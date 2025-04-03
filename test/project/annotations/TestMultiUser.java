package project.annotations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Assertions; // Fixed import for JUnit 5 Assertions
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.apis.networkapi.ImplementNetworkAPI; // Import ImplementNetworkAPI
import project.apis.networkapi.Screen; // Import Screen
import project.apis.networkapi.Window; // Import Window
import project.apis.networkapi.AskUser; // Import AskUser

public class TestMultiUser implements Runnable{

    // TODO 1: change the type of this variable to the name you're using for your @NetworkAPI
    // interface
    private ImplementNetworkAPI coordinator;

    @BeforeEach
    public void initializeComputeEngine() {
        // TODO 2: create an instance of the implementation of your @NetworkAPI; this is the component
        // that the user will make requests to
        // Store it in the 'coordinator' instance variable
        coordinator = new ImplementNetworkAPI(new Screen() {
            @Override
            public Window showWindow(AskUser askUser) {
                return new Window() {
                    // Minimal or no-op implementation
                };
            }
        });
    }
    @Test
    public void compareMultiAndSingleThreaded() throws Exception {
        int nThreads = 4;
        List<TestUser> testUsers = new ArrayList<>();
        for (int i = 0; i < nThreads; i++) {
            testUsers.add(new TestUser(coordinator));
        }

        // Run single threaded
        String singleThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.singleThreadOut.tmp";
        for (int i = 0; i < nThreads; i++) {
            File singleThreadedOut = new File(singleThreadFilePrefix + i);
            singleThreadedOut.deleteOnExit();
            testUsers.get(i).run(singleThreadedOut.getCanonicalPath());
        }

        // Run multi threaded
        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<Future<?>> results = new ArrayList<>();
        String multiThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.multiThreadOut.tmp";
        for (int i = 0; i < nThreads; i++) {
            File multiThreadedOut = new File(multiThreadFilePrefix + i);
            multiThreadedOut.deleteOnExit();
            String multiThreadOutputPath = multiThreadedOut.getCanonicalPath();
            TestUser testUser = testUsers.get(i);
            results.add(threadPool.submit(() -> testUser.run(multiThreadOutputPath)));
        }

        results.forEach(future -> {
            try {
                future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Check that the output is the same for multi-threaded and single-threaded
        List<String> singleThreaded = loadAllOutput(singleThreadFilePrefix, nThreads);
        List<String> multiThreaded = loadAllOutput(multiThreadFilePrefix, nThreads);
        Assertions.assertEquals(singleThreaded, multiThreaded);
    }

    private List<String> loadAllOutput(String prefix, int nThreads) throws IOException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nThreads; i++) {
            File multiThreadedOut = new File(prefix + i);
            result.addAll(Files.readAllLines(multiThreadedOut.toPath()));
        }
        return result;
    }
    //creates mulitimple thereads from ImplementNetworkAPI
    @Override
    public void run() {
        try{
            Callable<String> apiTask = () -> {
                Thread.sleep(1000);
                return "Task completed by " + Thread.currentThread().getName();
            };
            coordinator.makeApiCall(apiTask);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
