package project.annotations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import project.apis.networkapi.AskUser;
import project.apis.networkapi.BaseNetworkAPI; 
import project.apis.computeapi.DigitalRootPersistenceAPI;

public class TestUser implements Runnable {

    private final BaseNetworkAPI coordinator;
    private final DigitalRootPersistenceAPI computeApi;

    public TestUser(BaseNetworkAPI coordinator, DigitalRootPersistenceAPI computeApi) {
        this.coordinator = coordinator;
        this.computeApi = computeApi;
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

    /**
     * Run a CPU-intensive task using the coordinator and compute API
     * This method directly tests the ability to parallelize computation
     */
    public void runWithCpuIntensiveTask(String outputPath, int startNumber) {
        try {
            // Create output file
            File outFile = new File(outputPath);
            outFile.createNewFile();
            
            // Create a CPU-intensive task that computes digital roots for many large numbers
            Callable<String> task = () -> {
                List<String> results = new ArrayList<>();
                // Process a range of large numbers
                for (int i = 0; i < 1000; i++) {
                    int number = startNumber + i;
                    String digitalRoot = computeApi.processDigitalRootPersistence(number);
                    results.add(number + ":" + digitalRoot);
                }
                return String.join("\n", results);
            };
            
            // Execute the task via the coordinator API
            String result = coordinator.makeApiCall(task);
            
            // Write results to file
            Files.writeString(outFile.toPath(), result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        run("testUser.output.tmp");
    }
}
