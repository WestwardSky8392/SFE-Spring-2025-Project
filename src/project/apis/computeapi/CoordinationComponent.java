package project.apis.computeapi;

import project.apis.datastorage.DataStorageAPI;

public class CoordinationComponent {

    private final DataStorageAPI dataStorage;
    private SimpleCompute compute;

    public CoordinationComponent(DataStorageAPI dataStorage, SimpleCompute compute) {
        this.dataStorage = dataStorage;
        this.compute = compute;
    }

    public String startComputation(String inputLocation, String outputLocation) {
        try {
            // Step 1: Read data as an integer array from storage.
            int[] inputData = dataStorage.readData(inputLocation);
            
            // Check for missing data.
            if (inputData == null || inputData.length == 0) {
                return "Input data not found";
            }
            
            // Step 2: Convert the integer array to a comma-separated string.
            StringBuilder inputDataStr = new StringBuilder();
            for (int i = 0; i < inputData.length; i++) {
                inputDataStr.append(inputData[i]);
                if (i < inputData.length - 1) {
                    inputDataStr.append(",");
                }
            }
            
            // Step 3: Process the data.
            int[] results = compute.processData(inputData);
            
            // Step 4: Convert the integer array result into a string.
            StringBuilder resultStr = new StringBuilder();
            for (int i = 0; i < results.length; i++) {
                resultStr.append(results[i]);
                if (i < results.length - 1) {
                    resultStr.append(",");
                }
            }
            
            // Step 5: Write the result string to storage.
            dataStorage.writeData(outputLocation, resultStr.toString().chars().toArray());
            
            return "Computation completed successfully!";
        } catch (NumberFormatException e) {
            return "Invalid input data format";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred: " + e.getMessage();
        }
    }
}
