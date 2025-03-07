package project.apis.computeapi;

// Import necessary classes
import project.apis.datastorage.SimpleDataStorage;
import project.apis.computeapi.SimpleCompute;

public class Coordination {
    private SimpleDataStorage dataStorage;
    private SimpleCompute compute;

    // Constructor to initialize the components
    public Coordination(SimpleDataStorage dataStorage, SimpleCompute compute) {
        this.dataStorage = dataStorage;
        this.compute = compute;
    }

    // Method to start the computation
    public String startComputation(String inputLocation, String outputLocation) {
        try {
            // Step 1: Read integers from the data storage
            int[] inputData = dataStorage.readData(inputLocation);
            
            // Step 2: Pass the data to the compute component
            int[] results = compute.processData(inputData);
            
            // Step 3: Write the results back to the data storage
            dataStorage.writeData(outputLocation, results);
            
            // Return success message
            return "Computation completed successfully!";
        } catch (Exception e) {
            // Handle all exceptions
            e.printStackTrace();
            return "An error occurred: " + e.getMessage();
        }
    }
}
