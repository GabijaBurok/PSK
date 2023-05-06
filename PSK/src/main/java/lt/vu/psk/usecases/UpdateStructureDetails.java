package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Structure;
import lt.vu.psk.interceptors.LoggedInvocation;
import lt.vu.psk.persistance.StructureDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.Lock;

@ViewScoped
@Named
@Getter @Setter
public class UpdateStructureDetails implements Serializable {

    private Structure structure;

    @Inject
    private StructureDAO structureDAO;

    @Inject
    private EntityManager em;


    @PostConstruct
    private void init(){
        System.out.println("------------UpdateDutyDetails INIT CALLED------------");
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer structureId = Integer.parseInt(requestParameters.get("structureId"));
        this.structure = structureDAO.findOne(structureId);
    }

    @LoggedInvocation
    public String updateStructureMemberNumber() {
        Structure str = new Structure();
        str.setMember_number(this.structure.getMember_number());
        str.setStructure_name(this.structure.getStructure_name());
        str.setId(this.structure.getId());
        try {
            structureDAO.update(this.structure);

        } catch (OptimisticLockException e) {

            Structure str2 = structureDAO.findOne(this.structure.getId());
            System.out.println(this.structure.getId());
            structureDAO.update(str);

            return "/structureDetails.xhtml?faces-redirect=true&structureId=" + this.structure.getId() + "&error=optimistic-lock-exception";
        }
        return "structures.xhtml?structureId=" + this.structure.getDuties() + "&faces-redirect=true";
    }

}
