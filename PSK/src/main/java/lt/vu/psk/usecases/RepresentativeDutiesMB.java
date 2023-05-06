package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.mybatis.model.Duty;
import lt.vu.psk.mybatis.model.Representative;
import lt.vu.psk.mybatis.dao.DutyMapper;
import lt.vu.psk.mybatis.dao.RepresentativeMapper;
import lt.vu.psk.mybatis.dao.DutiesRepresentativesMapper;
import lt.vu.psk.mybatis.model.DutiesRepresentatives;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static javax.faces.context.FacesContext.getCurrentInstance;

@Model
public class RepresentativeDutiesMB implements Serializable {

    @Inject
    private DutyMapper dutyMapper;

    @Inject
    private RepresentativeMapper representativeMapper;

    @Inject
    private DutiesRepresentativesMapper dutiesRepresentativesMapper;

    @Getter @Setter
    private Representative representative;

    @Getter @Setter
    private Duty dutyToAdd = new Duty();

    @Getter
    private List<Duty> allExistingDuties;

    @Transactional
    public String addDutyForRepresentative(Integer dutyId) {
        if(dutiesRepresentativesMapper.getResultCountByDutyAndRepresentativeId(dutyId, this.representative.getId()) == 0) {
            DutiesRepresentatives dutiesRepresentatives = new DutiesRepresentatives();
            dutiesRepresentatives.setDutyId(dutyId);
            dutiesRepresentatives.setRepresentativeId(this.representative.getId());
            dutiesRepresentativesMapper.insert(dutiesRepresentatives);
        }

        return "/myBatis/representativesAndDuties?faces-redirect=true";
    }

    @Transactional
    public String addNewDutyForRepresentative() {
        if(dutyMapper.getResultCountByDutyName(dutyToAdd.getDutyName()) == 0) {
            dutyMapper.insert(dutyToAdd);
        }
        Duty addedDuty = dutyMapper.findByName(dutyToAdd.getDutyName());
        DutiesRepresentatives dutiesRepresentatives = new DutiesRepresentatives();
        dutiesRepresentatives.setDutyId(addedDuty.getId());
        dutiesRepresentatives.setRepresentativeId(this.representative.getId());
        dutiesRepresentativesMapper.insert(dutiesRepresentatives);

        return "/myBatis/representativesAndDuties?faces-redirect=true";
    }

    @PostConstruct
    private void init() {
        Map<String, String> requestParams = getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        Integer representativeId = Integer.parseInt(requestParams.get("representativeId"));
        this.representative = representativeMapper.selectByPrimaryKey(representativeId);
        this.allExistingDuties = this.dutyMapper.selectAll();
    }
}
