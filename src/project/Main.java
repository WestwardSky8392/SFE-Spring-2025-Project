package project;

import project.apis.computeapi.ImplementDigitalRootPersistenceAPI;
import project.apis.datastorage.ImplementDataStorage;
import project.apis.datastorage.ImplementDataStorageAPI;
import project.apis.networkapi.NetworkApiServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * Main class to test the implementations
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // Start the gRPC server
        Server server = ServerBuilder.forPort(8080)
            .addService(new NetworkApiServiceImpl())
            .build();

        System.out.println("gRPC Server started on port 8080");
        server.start();

        server.awaitTermination();
    }
}