package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Structure;
import lt.vu.psk.interceptors.LoggedInvocation;
import lt.vu.psk.persistance.StructureDAO;
import lt.vu.psk.qualifiers.MIF;
import lt.vu.psk.qualifiers.MIFStructureType;
import lt.vu.psk.qualifiers.StructureTypeProcessor;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Model
public class Structures {

    @Inject
    private StructureDAO structureDAO;

    @Inject @MIF
    StructureTypeProcessor structureTypeProcessor;

    @Inject @Any
    private MIFStructureType mifStructureType;

    @Getter @Setter
    private Structure structureToCreate = new Structure();

    @Getter
    private List<Structure> allStructures;

    @Getter
    private List<Structure> mifStructures;

    @PostConstruct
    public void init() {
        loadAllStructures();
        //loadMifStructures();
    }

    public void loadAllStructures() {
        this.allStructures = structureDAO.loadAll();
    }

    public void loadMifStructures() {

        List<Structure> structures = structureDAO.loadAll();
        this.mifStructures = structures.stream()
                                .filter(s -> s.getStructure_name().toUpperCase().contains("MIF"))
                                .collect(Collectors.toList());
    }

    @Transactional
    @LoggedInvocation
    public String createStructure() {
        this.structureDAO.persist(structureToCreate);
        System.out.println(structureTypeProcessor.StructureType());
        System.out.println(mifStructureType.StructureType());
        return "index?faces-redirect=true";
    }
}
