package project.apis.networkapi;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class BaseNetworkAPI {
    protected final Screen screen;

    public BaseNetworkAPI(Screen screen) {
        this.screen = screen;
    }

    public Window showWindow(AskUser askUser) {
        readAndWrite(askUser);
        return screen.showWindow(askUser);
    }

    protected void readAndWrite(AskUser user) {
        try {
            File inputFile = new File(user.getInputPath());
            List<String> lines = java.nio.file.Files.readAllLines(inputFile.toPath());

            File outputFile = new File(user.getOutputPath());
            java.nio.file.Files.write(outputFile.toPath(), lines);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SendInfo sendToProcess() {
        return new ValidInfo(new AskUser());
    }

    public abstract String makeApiCall(Callable<String> apiTask) throws Exception;
}