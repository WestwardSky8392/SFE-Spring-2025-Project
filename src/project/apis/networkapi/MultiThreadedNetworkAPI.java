package project.apis.networkapi;

import java.util.concurrent.Callable;

public class MultiThreadedNetworkAPI extends BaseNetworkAPI {
    private final Screen screen;

    public MultiThreadedNetworkAPI(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void showWindow(AskUser askUser) {
        System.out.println("MultiThreadedNetworkAPI: Showing window for AskUser with inputPath: " + askUser.getInputPath());
    }

    public String makeApiCall(Callable<String> apiTask) throws Exception {
        return apiTask.call();
    }
}