package lt.vu.psk.qualifiers;

import javax.enterprise.context.Dependent;

@MIF
@Dependent
public class MIFStructureType implements StructureTypeProcessor{

    @Override
    public String StructureType() {
        return "Structure type is MIF";
    }
}