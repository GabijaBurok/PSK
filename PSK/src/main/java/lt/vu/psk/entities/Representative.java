package lt.vu.psk.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Representative.findAll", query = "select r from Representative as r"),
        @NamedQuery(name = "Representative.findOne", query = "select r from Representative as r where r.first_name = :first_name and r.last_name = :last_name")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "REPRESENTATIVE")
public class Representative implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "REPRESENTATIVE_NAME", nullable = false)
    private String first_name;

    @Size(max = 50)
    @Column(name = "REPRESENTATIVE_LAST_NAME", nullable = false)
    private String last_name;

    @Column
    @ManyToMany
    @JoinTable(name = "DUTIES_REPRESENTATIVES",
            joinColumns = @JoinColumn(name = "REPRESENTATIVE_ID"),
            inverseJoinColumns = @JoinColumn(name = "DUTY_ID"))
    List<Duty> dutyList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Representative representative = (Representative) o;
        return id.equals(representative.id) && first_name.equals(representative.first_name) && last_name.equals(representative.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name);
    }
}
