package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Structure;
import lt.vu.psk.persistance.StructureDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Structures {

    @Inject
    private StructureDAO structureDAO;

    @Getter @Setter
    private Structure structureToCreate = new Structure();

    @Getter
    private List<Structure> allStructures;

    @PostConstruct
    public void init() {
        loadAllRepresentatives();
    }

    public void loadAllRepresentatives() {
        this.allStructures = structureDAO.loadAll();
    }

    @Transactional
    public String createStructure() {
        this.structureDAO.persist(structureToCreate);
        return "index?faces-redirect=true";
    }
}
