package lt.vu.psk.qualifiers;

import javax.enterprise.context.Dependent;

@Central
@Dependent
public class CentralStructureType implements StructureTypeProcessor{

    @Override
    public String StructureType() {
        return "Structure type is central";
    }
}
