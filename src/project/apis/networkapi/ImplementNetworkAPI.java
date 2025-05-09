package project.apis.networkapi;

import java.util.concurrent.Callable;

public class ImplementNetworkAPI {
    private final Screen screen;
    private final CoordinationEngine coordinationEngine;

    public ImplementNetworkAPI() {
        this.screen = null;
        this.coordinationEngine = null;
    }

    public ImplementNetworkAPI(CoordinationEngine coordinationEngine, Screen screen) {
        this.coordinationEngine = coordinationEngine;
        this.screen = screen;
    }

    public Window showWindow(AskUser askUser) {
        // Use CoordinationEngine to process input and output
        String inputKey = "input:" + askUser.getInputPath();
        String outputKey = "output:" + askUser.getOutputPath();
        coordinationEngine.startComputation(inputKey, outputKey);
        return screen.showWindow(askUser);
    }

    private void readAndWrite(AskUser user) {
        // Deprecated: now handled by CoordinationEngine
    }

    public SendInfo sendToProcess() {
        return new ValidInfo(new AskUser());
    }
        
    public String makeApiCall(Callable<String> apiTask) throws Exception {
        return apiTask.call();
    }
}
