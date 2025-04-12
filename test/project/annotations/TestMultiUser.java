package project.annotations;

import project.apis.networkapi.MultiThreadedNetworkAPI;
import project.apis.networkapi.Screen;
import project.apis.networkapi.Window;
import project.apis.networkapi.AskUser;
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

    @BeforeEach
    public void initializeComputeEngine() {
        coordinator = new MultiThreadedNetworkAPI(new Screen() {
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
            File file = new File(prefix + i);
            result.addAll(Files.readAllLines(file.toPath()));
        }
        return result;
    }

    @Override
    public void run() {
        try {
            Callable<String> apiTask = () -> {
                Thread.sleep(1000);
                return "Task completed by " + Thread.currentThread().getName();
            };
            coordinator.makeApiCall(apiTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
