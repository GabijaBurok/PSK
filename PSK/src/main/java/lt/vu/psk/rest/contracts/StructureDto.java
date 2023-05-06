package lt.vu.psk.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.psk.entities.Duty;

import java.util.List;

@Getter @Setter
public class StructureDto {

    private String StructureName;
    private Integer MemberNumber;
}
