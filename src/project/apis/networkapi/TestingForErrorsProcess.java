package project.apis.networkapi;
import project.apis.networkapi.ImplementNetworkAPI;
import project.apis.networkapi.SendInfo;
public class TestingForErrorsProcess {
    ImplementNetworkAPI implementNetworkAPI;
 /**
     * Exception handling to prevent uncaught exceptions from reaching
     * process boundary
     */
    public String testingForErrorsProcess(){
        try{
            implementNetworkAPI.sendToProcess();
            return "Successfully sent to Process!";
        }catch(Exception e){
            return "Failure sending to Process!";
        }
    }
}