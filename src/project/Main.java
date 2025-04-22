package project;

import project.apis.computeapi.ImplementDigitalRootPersistenceAPI;
import project.apis.datastorage.ImplementDataStorage;
import project.apis.datastorage.ImplementDataStorageAPI;
import project.apis.networkapi.AskUser;
import project.apis.networkapi.FileIOHandler;
import project.apis.networkapi.ImplementNetworkAPI;
import project.apis.networkapi.NetworkApiServiceImpl;
import project.apis.networkapi.Screen;
import project.apis.networkapi.ValidInfo;
import project.apis.datastorage.DataStorageApiGrpcClient;
import project.apis.datastorage.DataStorageAPI;

import java.util.Scanner;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * Main class to test the implementations
 */
public class Main {
    public static void main(String[] args) throws Exception {

        // GRPC Server setup
        Server server = ServerBuilder.forPort(8080)
            .addService((BindableService) new NetworkApiServiceImpl())
            .build();

        System.out.println("gRPC Server started on port 8080");
        server.start();

        // Use gRPC-based DataStorageAPI implementation
        DataStorageAPI dataStorage = new DataStorageApiGrpcClient("localhost", 9090);

        Scanner sc = new Scanner(System.in);
        System.out.println("Are you inputting data manually that involved a file? (y/n)");
        if (!sc.hasNextLine()) {
            // Case handle: No input detected
            System.out.println("No input detected. Please run this program in a terminal and provide input.");
            System.exit(1);
        }
        String choice = sc.nextLine();
        FileIOHandler f = new FileIOHandler();
        try {
            if (choice.equals("y") || choice.equals("yes") || choice.equals("Yes")) {
                System.out.println("Enter file: ");
                String fileName = sc.nextLine();
                f.readFile(fileName);
                System.out.println("File read and computation complete.");
            } else {
                System.out.println("Enter input separated by the spaces:");
                String numInput = sc.nextLine();
                String[] numbers = numInput.split(",");
                String numsArrString = "";
                for (int i = 0; i < numbers.length; i++) {
                    numsArrString += numbers[i];
                }
                AskUser askUser = new AskUser(numsArrString, " ", ' ');
                ImplementNetworkAPI networkAPI = new ImplementNetworkAPI();
                networkAPI.showWindow(askUser);
                networkAPI.sendToProcess();
                System.out.println("Computation complete.");
            }
            System.out.println("Task succeeded!");
        } catch (Exception e) {
            System.out.println("Task failed: " + e.getMessage());
            e.printStackTrace();
        }
        server.awaitTermination();
    }

    /** Test DigitalRootPersistenceAPI
     * Tests the DigitalRootPersistenceAPI implementation.
     */
    private static void testDigitalRootPersistenceAPI() {
        // Test DigitalRootPersistenceAPI
        ImplementDigitalRootPersistenceAPI api = new ImplementDigitalRootPersistenceAPI();
        project.apis.computeapi.FastDigitalRootPersistenceAPI fastApi = new project.apis.computeapi.FastDigitalRootPersistenceAPI();

        // Test cases from the test class
        int[] testNumbers = {9875, 12345, 999, 0, 1, 77};
        String[] expectedResults = {"2", "6", "9", "0", "1", "5"};

        System.out.println("Testing DigitalRootPersistenceAPI:");
        for (int i = 0; i < testNumbers.length; i++) {
            String result = api.processDigitalRootPersistence(testNumbers[i]);
            System.out.println("Input: " + testNumbers[i] + ", Expected: " + expectedResults[i] + ", Actual: " + result);
        }
        System.out.println();

        // Benchmark original vs optimized
        int largeNumber = 987654321;
        int iterations = 1_000_000;
        long start, end;

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            api.processDigitalRootPersistence(largeNumber + i % 100);
        }
        end = System.currentTimeMillis();
        System.out.println("Original implementation: " + (end - start) + " ms for " + iterations + " iterations.");

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            fastApi.processDigitalRootPersistence(largeNumber + i % 100);
        }
        end = System.currentTimeMillis();
        System.out.println("Optimized implementation: " + (end - start) + " ms for " + iterations + " iterations.");
    }
    
    /** Test StorageComputeAPI
     * Tests the StorageComputeAPI implementation.
     */
    private static void testStorageComputeAPI() {
        // Test StorageComputeAPI
        ImplementDataStorage api = new ImplementDataStorage();
        
        System.out.println("Testing StorageComputeAPI:");
        
        // Test writeData and readData
        String destination = "testDest";
        String data = "Sample Data";
        api.writeData(destination, data);
        System.out.println("writeData(\"" + destination + "\", \"" + data + "\")");
        
        String retrievedData = api.readData(destination);
        System.out.println("readData(\"" + destination + "\") = " + retrievedData);
    }
}