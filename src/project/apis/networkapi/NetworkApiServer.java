package project.apis.networkapi;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * Standalone server for the Network API
 * This allows the Network API to run in its own process
 */
public class NetworkApiServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Default port
        int port = 8080;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number. Using default 8080.");
            }
        }
        
        System.out.println("Starting NetworkAPI gRPC server on port " + port);
        
        Server server = ServerBuilder.forPort(port)
            .addService(new NetworkApiServiceImpl())
            .build();
        
        server.start();
        System.out.println("NetworkAPI gRPC server started successfully on port " + port);
        
        // Add shutdown hook to gracefully stop the server
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down NetworkAPI gRPC server...");
            server.shutdown();
            System.out.println("NetworkAPI gRPC server shutdown complete.");
        }));
        
        // Keep the server running
        server.awaitTermination();
    }
}