package lt.vu.psk.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class DecoratorImpl implements StructureDecorator{

    @Inject @Delegate @Any
    StructureDecorator structureDecorator;

    public Integer DecoratedInt(Integer integer){
        System.out.println("Decorator is used");
        return structureDecorator.DecoratedInt(integer) * 10;
    }

}