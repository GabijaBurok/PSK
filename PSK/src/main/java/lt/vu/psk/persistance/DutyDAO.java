package lt.vu.psk.persistance;

import lombok.Setter;
import lt.vu.psk.entities.Duty;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class DutyDAO {

    @Setter
    @PersistenceContext
    private EntityManager em;

    public List<Duty> loadAll() {return em.createNamedQuery("Duty.findAll", Duty.class).getResultList();}

    public void persist(Duty duty) {this.em.persist(duty);}

    public Duty findOne(Integer id) {return em.find(Duty.class, id);}

    public void setEm(EntityManager em) {this.em = em;}

    public Duty update(Duty duty) {return em.merge(duty);}

}
