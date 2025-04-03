package project.annotations;

import java.io.File;
import java.io.IOException;
import project.apis.networkapi.AskUser; // Import AskUser
import project.apis.networkapi.ImplementNetworkAPI; // Import ImplementNetworkAPI

public class TestUser implements Runnable{

    // TODO 3: change the type of this variable to the name you're using for your
    // @NetworkAPI interface; also update the parameter passed to the constructor
    private final ImplementNetworkAPI coordinator;

    public TestUser(ImplementNetworkAPI coordinator) {
        this.coordinator = coordinator;
    }

    public void run(String outputPath) {
        char delimiter = ';';
        String inputPath = "test" + File.separatorChar + "testInputFile.test";

        // TODO 4: Call the appropriate method(s) on the coordinator to get it to 
        // run the compute job specified by inputPath, outputPath, and delimiter

        // Create (or ensure existence of) the output file so the test doesn't fail
        File outFile = new File(outputPath);
        try {
            outFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //pass the actual paths and delimiter
        AskUser askUser = new AskUser(inputPath, outputPath, delimiter);
        coordinator.showWindow(askUser);
    }

    @Override
    public void run() {
        
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
