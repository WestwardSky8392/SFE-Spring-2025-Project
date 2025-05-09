package project;

import project.apis.computeapi.ImplementDigitalRootPersistenceAPI;
import project.apis.computeapi.FastDigitalRootPersistenceAPI;
import project.apis.datastorage.ImplementDataStorage;
import project.apis.datastorage.ImplementDataStorageAPI;
import project.apis.networkapi.AskUser;
import project.apis.networkapi.FileIOHandler;
import project.apis.networkapi.ImplementNetworkAPI;
import project.apis.networkapi.MultiThreadedNetworkAPI;
import project.apis.networkapi.Screen;
import project.apis.networkapi.ValidInfo;
import project.apis.datastorage.DataStorageApiGrpcClient;
import project.apis.datastorage.DataStorageAPI;
import project.apis.networkapi.NetworkApiClient;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main class that acts as the user-facing client
 * This is designed to connect to separate NetworkAPI and DataStorageAPI servers
 * Following the feedback to structure the application with three separate processes
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // Check if we should run benchmarks
        if (args.length > 0 && args[0].equals("benchmark")) {
            runBenchmarks();
            return;
        }

        System.out.println("Client application starting...");
        System.out.println("This client connects to the Network API and Data Storage servers.");
        
        // Use gRPC-based DataStorageAPI implementation which connects to the DataStorageApiServer
        DataStorageAPI dataStorage = null;
        try {
            dataStorage = new DataStorageApiGrpcClient("localhost", 9090);
            System.out.println("Connected to Data Storage server on port 9090");
        } catch (Exception e) {
            System.err.println("Failed to connect to Data Storage server: " + e.getMessage());
            System.err.println("Please make sure Data Storage server is running on port 9090");
            System.err.println("Run: java -cp <classpath> project.apis.datastorage.DataStorageApiGrpcServer");
            System.exit(1);
        }

        // Create NetworkAPI client that connects to NetworkApiServer
        NetworkApiClient networkClient = null;
        try {
            networkClient = new NetworkApiClient("localhost", 8080);
            System.out.println("Connected to Network API server on port 8080");
        } catch (Exception e) {
            System.err.println("Failed to connect to Network API server: " + e.getMessage());
            System.err.println("Please make sure Network API server is running on port 8080");
            System.err.println("Run: java -cp <classpath> project.apis.networkapi.NetworkApiServer");
            System.exit(1);
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Are you inputting data manually that involved a file? (y/n)");
        if (!sc.hasNextLine()) {
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
                System.out.println("Enter input separated by commas:");
                String numInput = sc.nextLine();
                String[] numbers = numInput.split(",");
                String numsArrString = "";
                for (int i = 0; i < numbers.length; i++) {
                    numsArrString += numbers[i];
                }
                
                // Make API call to the Network API server through the client
                String response = networkClient.makeApiCall("process:" + numsArrString);
                System.out.println("Server response: " + response);
                System.out.println("Computation complete.");
            }
            System.out.println("Task succeeded!");
        } catch (Exception e) {
            System.out.println("Task failed: " + e.getMessage());
            e.printStackTrace();
        }
        
        sc.close();
        System.out.println("Client application shutting down.");
    }

    // Benchmark methods remain unchanged
    /**
     * Run comprehensive benchmarks comparing original and optimized implementations
     */
    private static void runBenchmarks() {
        testDigitalRootPersistenceAPI();
    }

    /** 
     * Test DigitalRootPersistenceAPI
     * Tests and benchmarks both implementations.
     */
    private static void testDigitalRootPersistenceAPI() {
        // Test DigitalRootPersistenceAPI
        ImplementDigitalRootPersistenceAPI api = new ImplementDigitalRootPersistenceAPI();
        FastDigitalRootPersistenceAPI fastApi = new FastDigitalRootPersistenceAPI();

        // Test cases from the test class
        int[] testNumbers = {9875, 12345, 999, 0, 1, 77};
        String[] expectedResults = {"2", "6", "9", "0", "1", "5"};

        System.out.println("Testing DigitalRootPersistenceAPI:");
        System.out.println("================================");
        System.out.println("Original Implementation:");
        
        for (int i = 0; i < testNumbers.length; i++) {
            String result = api.processDigitalRootPersistence(testNumbers[i]);
            boolean correct = result.equals(expectedResults[i]);
            System.out.println("Input: " + testNumbers[i] + ", Expected: " + expectedResults[i] + 
                            ", Actual: " + result + (correct ? " ✓" : " ✗"));
        }
        
        System.out.println("\nOptimized Implementation:");
        for (int i = 0; i < testNumbers.length; i++) {
            String result = fastApi.processDigitalRootPersistence(testNumbers[i]);
            boolean correct = result.equals(expectedResults[i]);
            System.out.println("Input: " + testNumbers[i] + ", Expected: " + expectedResults[i] + 
                            ", Actual: " + result + (correct ? " ✓" : " ✗"));
        }
        
        System.out.println("\nBenchmark Results:");
        System.out.println("=================");

        // Benchmark with larger numbers to make the CPU impact more noticeable
        int[] largeNumbers = new int[1000];
        for (int i = 0; i < largeNumbers.length; i++) {
            largeNumbers[i] = 10_000_000 + i;  // Using 8-digit numbers for meaningful computation
        }
        
        int iterations = 10;
        long start, end;

        // Warm up the JVM to avoid bias from JIT compilation
        for (int i = 0; i < 3; i++) {
            api.processDigitalRootPersistence(largeNumbers[0]);
            fastApi.processDigitalRootPersistence(largeNumbers[0]);
        }

        start = System.currentTimeMillis();
        for (int j = 0; j < iterations; j++) {
            for (int num : largeNumbers) {
                api.processDigitalRootPersistence(num);
            }
        }
        end = System.currentTimeMillis();
        long originalTime = end - start;
        System.out.println("Original implementation: " + originalTime + " ms for " + iterations + 
                        " iterations of " + largeNumbers.length + " large numbers.");

        start = System.currentTimeMillis();
        for (int j = 0; j < iterations; j++) {
            for (int num : largeNumbers) {
                fastApi.processDigitalRootPersistence(num);
            }
        }
        end = System.currentTimeMillis();
        long fastTime = end - start;
        System.out.println("Optimized implementation: " + fastTime + " ms for " + iterations + 
                        " iterations of " + largeNumbers.length + " large numbers.");

        double improvementPercent = 100.0 * (originalTime - fastTime) / originalTime;
        System.out.println("Performance improvement: " + String.format("%.2f", improvementPercent) + "%");
        
        if (improvementPercent >= 10.0) {
            System.out.println("✓ Success: Optimization improved performance by more than 10%");
        } else {
            System.out.println("✗ Failed: Optimization did not improve performance by at least 10%");
        }
    }
}