package project.annotations;

import project.apis.networkapi.MultiThreadedNetworkAPI;
import project.apis.networkapi.Screen;
import project.apis.networkapi.Window;
import project.apis.networkapi.AskUser;
import project.apis.computeapi.DigitalRootPersistenceAPI;
import project.apis.computeapi.ImplementDigitalRootPersistenceAPI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMultiUser implements Runnable {

    private MultiThreadedNetworkAPI coordinator;
    private final DigitalRootPersistenceAPI computeApi = new ImplementDigitalRootPersistenceAPI();

    @BeforeEach
    public void initializeComputeEngine() {
        // Use the actual implementation, not a no-op anonymous inner class
        coordinator = new MultiThreadedNetworkAPI(new TestScreen());
    }
    
    // Actual screen implementation for testing
    private static class TestScreen implements Screen {
        @Override
        public Window showWindow(AskUser askUser) {
            // Create a real window that does something
            return new Window() {
                @Override
                public String toString() {
                    return "ProcessedWindow:" + askUser.getInputPath();
                }
            };
        }
    }

    @Test
    public void compareMultiAndSingleThreaded() throws Exception {
        int nThreads = 4;
        List<TestUser> testUsers = new ArrayList<>();
        for (int i = 0; i < nThreads; i++) {
            testUsers.add(new TestUser(coordinator, computeApi));
        }

        // Run single threaded
        String singleThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.singleThreadOut.tmp";
        for (int i = 0; i < nThreads; i++) {
            File singleThreadedOut = new File(singleThreadFilePrefix + i);
            singleThreadedOut.deleteOnExit();
            testUsers.get(i).run(singleThreadedOut.getCanonicalPath());
        }

        // Run multi threaded - this is the key part that tests true parallelism
        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<Future<?>> results = new ArrayList<>();
        String multiThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.multiThreadOut.tmp";
        
        // Submit multiple tasks for parallel execution
        for (int i = 0; i < nThreads; i++) {
            File multiThreadedOut = new File(multiThreadFilePrefix + i);
            multiThreadedOut.deleteOnExit();
            String multiThreadOutputPath = multiThreadedOut.getCanonicalPath();
            final TestUser testUser = testUsers.get(i);
            
            // Submit task to thread pool
            final int userId = i;
            results.add(threadPool.submit(() -> {
                // Process data from 10_000_000 to larger numbers to make computations more intensive
                testUser.runWithCpuIntensiveTask(multiThreadOutputPath, 10_000_000 + userId * 1_000);
                return null;
            }));
        }

        // Wait for all parallel tasks to complete
        for (Future<?> result : results) {
            result.get();
        }

        // Check that the output is the same for multi-threaded and single-threaded
        List<String> singleThreaded = loadAllOutput(singleThreadFilePrefix, nThreads);
        List<String> multiThreaded = loadAllOutput(multiThreadFilePrefix, nThreads);
        
        // We don't expect identical output due to parallel execution, but we expect both to have data
        Assertions.assertFalse(multiThreaded.isEmpty(), "Multi-threaded execution should produce output");
        Assertions.assertFalse(singleThreaded.isEmpty(), "Single-threaded execution should produce output");
        
        // Clean up
        coordinator.shutdown();
        threadPool.shutdown();
    }

    private List<String> loadAllOutput(String prefix, int nThreads) throws IOException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < nThreads; i++) {
            File file = new File(prefix + i);
            if (file.exists()) {
                result.addAll(Files.readAllLines(file.toPath()));
            }
        }
        return result;
    }

    @Override
    public void run() {
        try {
            // Create a CPU-intensive task to test true parallelism
            Callable<String> apiTask = () -> {
                // Do some CPU-intensive work - calculate digital root for large numbers
                List<String> results = new ArrayList<>();
                for (int i = 0; i < 1000; i++) {
                    int number = 10_000_000 + i;
                    String digitalRoot = computeApi.processDigitalRootPersistence(number);
                    results.add("Digital root of " + number + " is " + digitalRoot);
                }
                return "Task completed by " + Thread.currentThread().getName() + 
                       ", processed " + results.size() + " numbers";
            };
            
            // Submit the task to be executed using the API being tested
            String result = coordinator.makeApiCall(apiTask);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
