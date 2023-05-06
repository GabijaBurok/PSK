package lt.vu.psk.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.mybatis.dao.DutyMapper;
import lt.vu.psk.mybatis.model.Duty;

@Model
public class DutyRepresentativesMB implements Serializable {

    @Inject
    private DutyMapper dutyMapper;

    @Getter @Setter
    private Duty duty;

    @Getter @Setter
    private Duty dutyToCreate = new Duty();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer dutyId = Integer.parseInt(requestParameters.get("dutyId"));
        this.duty = dutyMapper.selectByPrimaryKey(dutyId);
    }
}
