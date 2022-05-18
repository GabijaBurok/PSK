package lt.vu.psk.persistance;

import lombok.Setter;
import lt.vu.psk.entities.Representative;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class RepresentativeDAO {

    @Setter
    @PersistenceContext
    private EntityManager em;

    public List<Representative> loadAll() {return em.createNamedQuery("Representative.findAll", Representative.class). getResultList();}

    public void persist(Representative representative) {this.em.persist(representative);}

    public Representative findOne(Integer id) {return em.find(Representative.class, id);}

    public void setEm(EntityManager em) {this.em = em;}

    public Representative findByRepresentative(String first_name, String last_name) {
        try
        {
            return em.createNamedQuery("Representative.findOne", Representative.class)
                    .setParameter("first_name", first_name)
                    .setParameter("last_name", last_name)
                    .getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }
}
