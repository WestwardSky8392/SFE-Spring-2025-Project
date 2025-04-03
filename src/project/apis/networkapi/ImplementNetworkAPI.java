package project.apis.networkapi;

import project.apis.networkapi.Screen;
import project.apis.networkapi.AskUser;
import project.apis.networkapi.Window;
import project.apis.networkapi.SendInfo;
import project.apis.networkapi.ValidInfo;

import java.io.File;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

/**
 * Prototype implementation of the NetworkAPI.
 * This class uses a Screen dependency to handle UI interactions.
 */
public class ImplementNetworkAPI { 

    private final Screen screen;
    private static final int THREAD_POOL_SIZE = 12;
    private final ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    /**
     * Constructor accepting a Screen dependency.
     * @param screen The Screen implementation to use.
     */
    public ImplementNetworkAPI(Screen screen) {
        this.screen = screen;
    }

    /**
     * Delegates the call to the injected Screen instance.
     * Demonstrates basic file reading & writing for test .tmp files.
     * @param askUser User interaction request object.
     * @return The Window instance from the Screen.
     */
    public Window showWindow(AskUser askUser) {
        // Minimal demonstration of reading/writing for the test .tmp files
        // using the user-provided input/output paths
        readAndWrite(askUser);
        return screen.showWindow(askUser);
    }

    /**
     * Example method to demonstrate file reading and writing.
     * @param user User interaction request object.
     */
    private void readAndWrite(AskUser user) {
        try {
            // read from user’s inputPath
            File inputFile = new File(user.getInputPath());
            List<String> lines = java.nio.file.Files.readAllLines(inputFile.toPath());

            // write to user’s outputPath
            File outputFile = new File(user.getOutputPath());
            java.nio.file.Files.write(outputFile.toPath(), lines);

            // Possibly use user.getDelimiter() for processing each line
            // (split lines, parse them, transform them, etc.)

        } catch (Exception e) {
            // handle/log errors
            e.printStackTrace();
        }
    }

    /**
     * Placeholder method to send information for processing.
     * @return A SendInfo instance.
     */
    public SendInfo sendToProcess() {
        // Create and return a ValidInfo instance that implements SendInfo
        AskUser askUser = new AskUser(); 
        SendInfo info = new ValidInfo(askUser); 
        return info;
    }
    
    public Future<String> makeApiCall(Callable<String> apiTask) {
        return executor.submit(apiTask);
    }

    // Shutdown method to clean up resources
    public void shutdown() {
        executor.shutdown();
    }  
}
