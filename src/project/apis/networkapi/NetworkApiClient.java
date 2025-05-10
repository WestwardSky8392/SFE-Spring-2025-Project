package project.apis.networkapi;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import networkapi.NetworkAPIGrpc;
import networkapi.Networkapi.AskUserRequest;
import networkapi.Networkapi.WindowResponse;
import networkapi.Networkapi.ApiTaskRequest;
import networkapi.Networkapi.ApiTaskResponse;

import java.util.concurrent.TimeUnit;

/**
 * Client for connecting to the NetworkAPI server via gRPC
 * This allows the client application to communicate with the NetworkAPI service
 */
public class NetworkApiClient {
    private final NetworkAPIGrpc.NetworkAPIBlockingStub blockingStub;
    private final ManagedChannel channel;

    public NetworkApiClient(String host, int port) {
        // Create a channel to the server
        this.channel = ManagedChannelBuilder.forAddress(host, port)
            .usePlaintext() // For simplicity; not recommended for production
            .build();
        
        // Create a blocking stub for synchronous calls
        this.blockingStub = NetworkAPIGrpc.newBlockingStub(channel);
    }

    /**
     * Shows a window with the given input and output paths
     */
    public String showWindow(String inputPath, String outputPath) {
        AskUserRequest request = AskUserRequest.newBuilder()
            .setInputPath(inputPath)
            .setOutputPath(outputPath)
            .build();
            
        WindowResponse response = blockingStub.showWindow(request);
        return response.getMessage();
    }

    /**
     * Makes an API call with the given task
     */
    public String makeApiCall(String task) {
        ApiTaskRequest request = ApiTaskRequest.newBuilder()
            .setTask(task)
            .build();
            
        ApiTaskResponse response = blockingStub.makeApiCall(request);
        return response.getResult();
    }

    /**
     * Shuts down the client's channel
     */
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}