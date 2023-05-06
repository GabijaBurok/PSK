package lt.vu.psk.alternatives;

import javax.enterprise.context.Dependent;

@Dependent
public class StructureMessage implements Message{

    @Override
    public String WriteMessage() {
        return "Structure created";
    }

    public StructureMessage() {

    }
}
