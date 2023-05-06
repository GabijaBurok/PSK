package lt.vu.psk.persistance;

import lombok.Setter;
import lt.vu.psk.entities.Structure;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class StructureDAO {

    @Setter
    @PersistenceContext
    private EntityManager em;

    public List<Structure> loadAll() {
        return em.createNamedQuery("Structure.findAll", Structure.class).getResultList();
    }

    public void persist(Structure structure) {
        this.em.persist(structure);
    }

    public Structure findOne(Integer id) {
        return em.find(Structure.class, id);
    }

    public Structure update(Structure structure) {return em.merge(structure);}

    public void flush() {this.em.flush();}
}
