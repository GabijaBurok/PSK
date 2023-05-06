package lt.vu.psk.usecases;

import lt.vu.psk.interceptors.LoggedInvocation;
import lt.vu.psk.services.MemberNumberGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateStructureMemberNumber implements Serializable {

    @Inject
    MemberNumberGenerator memberNumberGenerator;

    private CompletableFuture<Integer> memberNumberGenerationTask = null;

    @LoggedInvocation
    public String generateNewMemberNumber(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        memberNumberGenerationTask = CompletableFuture.supplyAsync(() -> memberNumberGenerator.generateMemberNumber());

        return "/structureDetails.xhtml?faces-redirect=true&structureId=" + requestParameters.get("structureId");
    }

    public boolean isMemberNumberGenerationRunning(){
        return memberNumberGenerationTask != null && !memberNumberGenerationTask.isDone();
    }

    public String getMemberNumberGeneratorStatus() throws ExecutionException, InterruptedException {
        if (memberNumberGenerationTask == null) {
            return null;
        } else if (isMemberNumberGenerationRunning()){
            return "Member Number Generator in progress";
        }
        return "Suggested Member Number: " + memberNumberGenerationTask.get();
    }
}
