package lt.vu.psk.decorators;

import javax.enterprise.context.Dependent;

@Dependent
public class Decorator implements StructureDecorator{

    @Override
    public Integer DecoratedInt(Integer integer) {
        return integer;
    }
}
