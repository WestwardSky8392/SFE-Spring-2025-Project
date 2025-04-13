package project.apis.networkapi;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;

public class MultiThreadedNetworkAPI extends BaseNetworkAPI {
    private static final int THREAD_POOL_SIZE = 12; 
    private final ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public MultiThreadedNetworkAPI(Screen screen) {
        super(screen);
    }

    @Override
    public String makeApiCall(Callable<String> apiTask) throws Exception {
        return executor.submit(apiTask).get();
    }

    @Override
    public Window showWindow(AskUser askUser) {
        System.out.println("MultiThreadedNetworkAPI: Showing window for AskUser with inputPath: " + askUser.getInputPath());
        return screen.showWindow(askUser); // Delegate to the `screen` implementation
    }

    public void shutdown() {
        executor.shutdown();
    }
}