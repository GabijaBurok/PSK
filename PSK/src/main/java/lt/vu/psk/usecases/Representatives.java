package lt.vu.psk.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Representative;
import lt.vu.psk.persistance.RepresentativeDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Representatives {

    @Inject
    private RepresentativeDAO representativeDAO;

    @Getter @Setter
    private Representative representativeToCreate = new Representative();

    @Getter
    private List<Representative> allRepresentatives;

    @PostConstruct
    public void init() {
        loadRepresentatives();
    }

    public void loadRepresentatives() {
        this.allRepresentatives = representativeDAO.loadAll();
    }

    @Transactional
    public String createRepresentative() {
        this.representativeDAO.persist(representativeToCreate);
        return "index?faces-redirect=true";
    }
}
