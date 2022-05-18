package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Duty;
import lt.vu.psk.entities.Structure;
import lt.vu.psk.persistance.DutyDAO;
import lt.vu.psk.persistance.StructureDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class StructureDuties implements Serializable {

    @Inject
    private StructureDAO structureDAO;

    @Inject
    private DutyDAO dutyDAO;

    @Getter @Setter
    private Structure structure;

    @Getter @Setter
    private Duty dutyToCreate = new Duty();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer structureId = Integer.parseInt(requestParameters.get("structureId"));
        this.structure = structureDAO.findOne(structureId);
    }

    @Transactional
    public String createDuty() {
        dutyToCreate.setStructure(this.structure);
        dutyDAO.persist(dutyToCreate);
        return "duties?faces-redirect=true&structureId=" + this.structure.getId();
    }
}
