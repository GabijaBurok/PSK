package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.mybatis.dao.RepresentativeMapper;
import lt.vu.psk.mybatis.model.Representative;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class RepresentativesAndDutiesMB implements Serializable {

    @Inject
    private RepresentativeMapper representativeMapper;

    @Getter
    private List<Representative> allRepresentatives;

    @Getter @Setter
    private Representative representativeToCreate = new Representative();

    @PostConstruct
    private void init() {
        loadAllRepresentatives();
    }

    @Transactional
    public String createRepresentative() {
        representativeMapper.insert(representativeToCreate);
        return "/myBatis/representativesAndDuties?faces-redirect=true";
    }

    private void loadAllRepresentatives() {
        allRepresentatives = representativeMapper.selectAll();
    }
}
