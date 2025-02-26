package project;

// Import the TestDataStorageAPI class
import test.project.TestDataStorageAPI;

public class Main {
    public static void main(String[] args) {
        TestDataStorageAPI testDataStorageAPI = new TestDataStorageAPI();
        
        // Example of running tests
        System.out.println("Running Tests...");

        // Add your test methods here
        testConstructor(testDataStorageAPI);
        testStoreData(testDataStorageAPI);
        testRetrieveData(testDataStorageAPI);

        System.out.println("All tests completed.");
    }

    private static void testConstructor(TestDataStorageAPI api) {
        // Add your test logic for the constructor
        System.out.println("Testing Constructor...");
        // Example assertions
        // assert api != null : "Constructor failed";
    }

    private static void testStoreData(TestDataStorageAPI api) {
        // Add your test logic for storing data
        System.out.println("Testing Store Data...");
        // Example assertions
        api.storeData("key", "value");
        assert "value".equals(api.retrieveData("key")) : "Store Data failed";
    }

    private static void testRetrieveData(TestDataStorageAPI api) {
        // Add your test logic for retrieving data
        System.out.println("Testing Retrieve Data...");
        // Example assertions
         api.storeData("key", "value");
         String value = api.retrieveData("key");
         assert "value".equals(value) : "Retrieve Data failed";
    }
}