package project.annotations;

import java.io.File;
import java.io.IOException;
import project.apis.networkapi.AskUser;
import project.apis.networkapi.BaseNetworkAPI;

public class TestUser implements Runnable {

    private final BaseNetworkAPI coordinator;

    public TestUser(BaseNetworkAPI coordinator) {
        this.coordinator = coordinator;
    }

    public void run(String outputPath) {
        char delimiter = ';';
        String inputPath = "test" + File.separatorChar + "testInputFile.test";

        // Create (or ensure existence of) the output file so the test doesn't fail
        File outFile = new File(outputPath);
        try {
            outFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Pass the actual paths and delimiter
        AskUser askUser = new AskUser(inputPath, outputPath, delimiter);
        coordinator.showWindow(askUser);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
