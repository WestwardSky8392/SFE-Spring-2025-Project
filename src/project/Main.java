package project;

import project.apis.computeapi.Computation;
import project.apis.computeapi.ImplementDigitalRootPersistenceAPI;
import project.apis.computeapi.SimpleCompute;
import project.apis.computeapi.SimpleComputeSlow;
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

import javax.management.RuntimeErrorException;

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
        benchmarkTest();

        try {
            if (choice.equals("y") || choice.equals("yes") || choice.equals("Yes")) {
                System.out.println("Enter file: ");
                String fileName = sc.nextLine();
                f.readFile(fileName);
                System.out.println("File read and computation complete.");
                sc.close();
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
                sc.close();
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
        
        // Test cases from the test class
        int[] testNumbers = {9875, 12345, 999, 0, 1, 77};
        String[] expectedResults = {"2", "6", "9", "0", "1", "5"};
        
        System.out.println("Testing DigitalRootPersistenceAPI:");
        for (int i = 0; i < testNumbers.length; i++) {
            String result = api.processDigitalRootPersistence(testNumbers[i]);
            System.out.println("Input: " + testNumbers[i] + ", Expected: " + expectedResults[i] + ", Actual: " + result);
        }
        System.out.println();
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

    private static void benchmarkTest(){
        int instance_variable = 6;
        long compuatationTimeFast = timeComputation(null,new SimpleCompute(), instance_variable);
        long compuatationTimeSlow = timeComputation(new SimpleComputeSlow(), null, instance_variable);

        int[] instance_inputData = {1, 2, 3, 4, 5};
        long dataStoreTimeFast = timeDataStore(instance_inputData);
        long dataStoreTimeSlow = timeDataStore(instance_inputData);
        
        System.out.println("Compute time fast: " + compuatationTimeFast);
        System.out.println("Compute time slow: " + compuatationTimeSlow);
        
        if((compuatationTimeSlow - compuatationTimeFast) / compuatationTimeSlow > .1){
            System.out.println("Computations are working");
        }else{
            throw new RuntimeException();
        }

        System.out.println("Data Store time fast: " + dataStoreTimeFast);
        System.out.println("Data Store time slow: " + dataStoreTimeSlow);

        if((dataStoreTimeSlow - dataStoreTimeFast) / dataStoreTimeSlow > .1){
            System.out.println("Data Store is workinng");
        }else{
            throw new RuntimeException();
        }
    }
    
    private static long timeComputation(SimpleComputeSlow simpleComputeSlow, SimpleCompute simpleCompute,int userInput){
        if(simpleCompute != null && simpleComputeSlow == null){
            long timeStart = System.currentTimeMillis();
            simpleCompute = new SimpleCompute();
            simpleCompute.computeUserInput(userInput);
            long timeEnd = System.currentTimeMillis();
            return timeEnd-timeStart;
        }else{
        long timeStart = System.currentTimeMillis();
        simpleComputeSlow = new SimpleComputeSlow();
        simpleComputeSlow.computeUserInput(userInput);
        long timeEnd = System.currentTimeMillis();
        return timeEnd-timeStart;
        }
    }

    private static long timeDataStore(int [] inputData){

        long timeStart = System.currentTimeMillis();
        SimpleCompute simpleCompute = new SimpleCompute();
        simpleCompute.processData(inputData);
        long timeEnd = System.currentTimeMillis();
        return timeEnd-timeStart;

    }
}