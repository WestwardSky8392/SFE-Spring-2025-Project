package project.apis.networkapi;

import io.grpc.stub.StreamObserver;

import networkapi.Networkapi.AskUserRequest;
import networkapi.Networkapi.WindowResponse;
import networkapi.Networkapi.ApiTaskRequest;
import networkapi.Networkapi.ApiTaskResponse;
import networkapi.NetworkAPIGrpc;

import project.apis.computeapi.DigitalRootPersistenceAPI;
import project.apis.computeapi.ImplementDigitalRootPersistenceAPI;
import project.apis.datastorage.DataStorageAPI;
import project.apis.datastorage.DataStorageApiGrpcClient;

/**
 * Implementation of the NetworkAPI gRPC service
 * This class demonstrates proper component communication by delegating to both
 * the data storage component and the computation component
 */
public class NetworkApiServiceImpl extends NetworkAPIGrpc.NetworkAPIImplBase {
    // Component instances for delegation
    private final DigitalRootPersistenceAPI computeApi;
    private DataStorageAPI dataStorageApi;

    public NetworkApiServiceImpl() {
        // Initialize computation component
        this.computeApi = new ImplementDigitalRootPersistenceAPI();
        
        // Try to connect to the DataStorage gRPC server
        try {
            this.dataStorageApi = new DataStorageApiGrpcClient("localhost", 9090);
            System.out.println("NetworkApiService successfully connected to DataStorage server");
        } catch (Exception e) {
            System.err.println("Warning: Could not connect to DataStorage server: " + e.getMessage());
            System.err.println("Will operate with limited functionality");
        }
    }

    @Override
    public void showWindow(AskUserRequest request, StreamObserver<WindowResponse> responseObserver) {
        System.out.println("NetworkAPI: Received request to show window for: " + request.getInputPath());
        String result = "Processing window request";
        
        try {
            // Delegate file operations to the data storage component
            if (dataStorageApi != null) {
                // Store information about the request
                String key = "window_request:" + request.getInputPath();
                String data = "inputPath=" + request.getInputPath() + 
                             ",outputPath=" + request.getOutputPath();
                dataStorageApi.writeData(key, data.chars().toArray());
                result = "Successfully processed window request through data storage component";
            }
        } catch (Exception e) {
            System.err.println("Error delegating to data storage: " + e.getMessage());
            result = "Error: " + e.getMessage();
        }
        
        WindowResponse response = WindowResponse.newBuilder()
            .setMessage(result)
            .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void makeApiCall(ApiTaskRequest request, StreamObserver<ApiTaskResponse> responseObserver) {
        System.out.println("NetworkAPI: Received API task: " + request.getTask());
        String result = "Unknown task";
        
        try {
            String task = request.getTask();
            
            // Check if this is a computation task
            if (task.startsWith("process:")) {
                // Extract the number from the task
                String numberStr = task.substring("process:".length());
                try {
                    // Parse the number
                    int number = Integer.parseInt(numberStr.trim());
                    
                    // Delegate to computation component
                    String computeResult = computeApi.processDigitalRootPersistence(number);
                    
                    // Store result using data storage component if available
                    if (dataStorageApi != null) {
                        String key = "result:" + number;
                        dataStorageApi.writeData(key, computeResult.chars().toArray());
                    }
                    
                    result = "Result for " + number + ": " + computeResult;
                } catch (NumberFormatException e) {
                    result = "Error: Invalid number format: " + numberStr;
                }
            } else if (task.startsWith("retrieve:")) {
                // Data retrieval task
                String key = task.substring("retrieve:".length());
                
                // Delegate to data storage component
                if (dataStorageApi != null) {
                    try {
                        int[] data = dataStorageApi.readData(key);
                        result = "Retrieved data: " + new String(data, 0, data.length);
                    } catch (Exception e) {
                        result = "Error retrieving data: " + e.getMessage();
                    }
                } else {
                    result = "Error: Data storage service unavailable";
                }
            } else {
                result = "Completed task: " + task;
            }
        } catch (Exception e) {
            System.err.println("Error processing API task: " + e.getMessage());
            result = "Error: " + e.getMessage();
        }
        
        ApiTaskResponse response = ApiTaskResponse.newBuilder()
            .setResult(result)
            .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
