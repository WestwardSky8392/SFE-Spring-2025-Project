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

    public void shutdown() {
        executor.shutdown();
    }
}