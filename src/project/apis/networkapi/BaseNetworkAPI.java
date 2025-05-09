package project.apis.networkapi;

import java.util.concurrent.Callable;

public abstract class BaseNetworkAPI {
    protected final Screen screen;
    protected final CoordinationEngine coordinationEngine;

    public BaseNetworkAPI(Screen screen, CoordinationEngine coordinationEngine) {
        this.screen = screen;
        this.coordinationEngine = coordinationEngine;
    }

    public Window showWindow(AskUser askUser) {
        // Use CoordinationEngine to process input and output
        String inputKey = "input:" + askUser.getInputPath();
        String outputKey = "output:" + askUser.getOutputPath();
        coordinationEngine.startComputation(inputKey, outputKey);
        return screen.showWindow(askUser);
    }

    @Deprecated
    protected void readAndWrite(AskUser user) {
        // Deprecated: now handled by CoordinationEngine
    }

    public SendInfo sendToProcess() {
        return new ValidInfo(new AskUser());
    }

    public abstract String makeApiCall(Callable<String> apiTask) throws Exception;
}