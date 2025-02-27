public class CoordinationComponent {
    private DataStorage dataStorage;
    private Compute compute;

    // Constructor to initialize the components
    public CoordinationComponent(DataStorage dataStorage, Compute compute) {
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
            // Handle any errors
            e.printStackTrace();
            return "An error occurred during the computation.";
        }
    }
}