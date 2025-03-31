package project.apis.computeapi;

// Import necessary classes
import project.apis.datastorage.SimpleDataStorage;
import project.apis.computeapi.SimpleCompute;

public class Coordination {
    private final SimpleCompute compute;

    // Constructor to initialize the compute component
    public Coordination(SimpleCompute compute) {
        this.compute = compute;
    }

    // Method to execute computation
    public void execute(int[] inputData) {
        int[] results = compute.processData(inputData); // Use processData
        // Handle results (implementation can be added as needed)
    }
}
