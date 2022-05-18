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
        @NamedQuery(name = "Structure.findAll", query = "select s from Structure as s")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "STRUCTURE")
public class Structure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "STRUCTURE_NAME", nullable = false, unique = true)
    private String structure_name;

    @OneToMany(mappedBy = "structure", fetch = FetchType.EAGER)
    private List<Duty> duties = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Structure structure = (Structure) o;
        return id.equals(structure.id) && structure_name.equals(structure.structure_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, structure_name);
    }
}
