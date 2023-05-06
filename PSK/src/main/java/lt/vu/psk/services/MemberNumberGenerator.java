package lt.vu.psk.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class MemberNumberGenerator implements Serializable {

    public Integer generateMemberNumber(){
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
        }
        Integer generatedMemberNumber = new Random().nextInt(25);
        return generatedMemberNumber;
    }
}
