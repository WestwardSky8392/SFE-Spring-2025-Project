package project.apis.networkapi;

import project.annotations.ProcessAPIPrototype;

public class ValidInfo implements SendInfo {
    private AskUser validInt;

    public ValidInfo(AskUser validInt) {
        this.validInt = validInt;
    }

    public SendInfo sendToProcess() {
        return this;
    }
    
    public String getProcessingMessage() {
        return "Processing completed";
    }
    
    public boolean isSuccess() {
        return true;
    }
    
    // If you know what methods are required by SendInfo, implement them here
    // For example:
    // public void processData() { ... }
    // public AskUser getAskUser() { return validInt; }
}
