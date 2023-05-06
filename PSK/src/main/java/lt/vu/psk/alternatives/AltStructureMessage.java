package lt.vu.psk.alternatives;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

@Dependent
@Alternative
public class AltStructureMessage implements Message{

    @Override
    public String WriteMessage() {
        return "Alternative structure created";
    }

    public AltStructureMessage() {

    }
}
