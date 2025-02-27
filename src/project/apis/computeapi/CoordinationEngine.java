package project.apis.computeapi;

import project.apis.datastorage.DataStorageAPI;

/**
 * Coordinates the computation and data storage components.
 * It handles receiving requests, fetching data, performing computations, and storing results.
 */
public class CoordinationEngine {
    private DataStorageAPI dataStorage;
    private Computation computation;

    /**
     * Talks with the specified data storage and computation components.
     *
     * @param dataStorage The data storage component.
     * @param computation The computation component.
     */
    public CoordinationEngine(DataStorageAPI dataStorage, Computation computation) {
        this.dataStorage = dataStorage;
        this.computation = computation;
    }

    /**
     * Starts the computation process by fetching input data, performing the computation,
     * and storing the result.
     *
     * @param inputKey  The key to fetch the input data.
     * @param outputKey The key to store the computation result.
     * @return 
     */
    public String startComputation(String inputKey, String outputKey) {
        // Fetch data from storage
        String inputData = dataStorage.fetchData(inputKey);
        if (inputData == null) {
            return "Input data not found";
        }

        // Convert input data to integer
        int number;
        try {
            number = Integer.parseInt(inputData);
        } catch (NumberFormatException e) {
            return "Invalid input data format";
        }

        // Perform computation and returns result back to Computation.java
        String result = computation.computeUserInput(number);

        // Store result in storage
        dataStorage.storeData(outputKey, result);

        return "Computation completed successfully";
    }
}
