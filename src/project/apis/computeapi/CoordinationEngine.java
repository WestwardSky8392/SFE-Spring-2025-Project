package project.apis.computeapi;

import project.apis.datastorage.DataStorageAPI;

/**
 * Coordinates the computation and data storage components.
 * It handles receiving requests, fetching data, performing computations, and storing results.
 */
public class CoordinationEngine {
    private final DataStorageAPI dataStorage;
    private final Computation computation;

    /**
     * Talks with the specified data storage and computation components.
     *
     * @param dataStorage The data storage component.
     * @param computation The computation component.
     */
    public CoordinationEngine(DataStorageAPI dataStorage, Computation computation) {
        if (dataStorage == null || computation == null) {
            throw new IllegalArgumentException("DataStorage and Computation cannot be null");
        }
        this.dataStorage = dataStorage;
        this.computation = computation;
    }

    /**
     * Starts the computation process by fetching input data, performing the computation,
     * and storing the result.
     *
     * @param inputKey  The key to fetch the input data.
     * @param outputKey The key to store the computation result.
     * @return A string message indicating the result of the computation.
     */
    public String startComputation(String inputKey, String outputKey) {
        if (inputKey == null || inputKey.isEmpty()) {
            throw new IllegalArgumentException("Input key cannot be null or empty");
        }
        if (outputKey == null || outputKey.isEmpty()) {
            throw new IllegalArgumentException("Output key cannot be null or empty");
        }

        int[] inputData;
        try {
            inputData = dataStorage.readData(inputKey);
        } catch (Exception e) {
            // In a real application, you might log this exception.
            return "Input data not found";
        }

        if (inputData == null) {
            return "Input data not found";
        }

        String inputDataStr = new String(inputData, 0, inputData.length);

        int number;
        try {
            number = Integer.parseInt(inputDataStr);
        } catch (NumberFormatException e) {
            return "Invalid input data format";
        }

        // Compute the result using the provided computation component.
        String result = computation.computeUserInput(number);

        try {
            dataStorage.writeData(outputKey, result.chars().toArray());
        } catch (Exception e) {
            // In a real application, you might log this exception.
            return "Error storing result";
        }

        return "Computation completed successfully";
    }
}
