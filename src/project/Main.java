package project;

import project.apis.computeapi.ImplementDigitalRootPersistenceAPI;
import project.apis.datastorage.ImplementDataStorage;
import project.apis.datastorage.ImplementDataStorageAPI;

/**
 * Main class to test the implementations.
 */
public class Main {
    public static void main(String[] args) {
        testDigitalRootPersistenceAPI();
        testDataStorageAPI();
        testStorageComputeAPI();
    }
    
    /**
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
    
    /**
     * Tests the DataStorageAPI implementation.
     */
    private static void testDataStorageAPI() {
        // Test DataStorageAPI
        ImplementDataStorageAPI api = new ImplementDataStorageAPI();
        
        System.out.println("Testing DataStorageAPI:");
        
        // Test fetchData
        String key = "testKey";
        String value = api.fetchData(key);
        System.out.println("fetchData(\"" + key + "\") = " + value);
        
        // Test storeData
        key = "newKey";
        value = "newValue";
        api.storeData(key, value);
        String retrievedValue = api.fetchData(key);
        System.out.println("storeData(\"" + key + "\", \"" + value + "\")");
        System.out.println("fetchData(\"" + key + "\") = " + retrievedValue);
        System.out.println();
    }
    
    /**
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
        String retrievedData = api.readData(destination);
        System.out.println("writeData(\"" + destination + "\", \"" + data + "\")");
        System.out.println("readData(\"" + destination + "\") = " + retrievedData);
    }
}