package project.apis.computeapi;

import project.apis.datastorage.DataStorageAPI;

public class CoordinationComponent {

    private final DataStorageAPI dataStorage;
    private final SimpleCompute compute;

    public CoordinationComponent(DataStorageAPI dataStorage, SimpleCompute compute) {
        this.dataStorage = dataStorage;
        this.compute = compute;
    }

    public String startComputation(String inputLocation, String outputLocation) {
        try {
            // Step 1: Read data as an integer array from storage (now via gRPC under the hood).
            int[] inputData = dataStorage.readData(inputLocation);

            if (inputData == null || inputData.length == 0) {
                return "Input data not found";
            }

            // Step 2: Process the data
            int[] results = compute.processData(inputData);

            // Step 3: Write the result to the data store (via gRPC)
            dataStorage.writeData(outputLocation, results);

            return "Computation completed successfully!";
        } catch (NumberFormatException e) {
            return "Invalid input data format";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred: " + e.getMessage();
        }
    }
}

