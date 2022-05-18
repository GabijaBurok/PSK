package lt.vu.psk.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = "Duty.findAll", query = "select d from Duty as d")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "DUTY")
public class Duty implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "DUTY_NAME", nullable = false)
    private String duty_name;

    @ManyToOne
    @JoinColumn(name = "STRUCTURE_ID")
    private Structure structure;

    @ManyToMany(mappedBy = "dutyList")
    List<Representative> representativeList;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Duty duty = (Duty) o;
        return id.equals(duty.id) && duty_name.equals(duty.duty_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, duty_name);
    }

}
