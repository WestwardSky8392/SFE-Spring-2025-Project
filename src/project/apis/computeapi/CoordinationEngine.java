package project.apis.computeapi;

import project.apis.datastorage.DataStorageAPI;

/**
 * Coordinates the computation and data storage components.
 * It handles receiving requests, fetching data, performing computations, and storing results.
 */
public class CoordinationEngine {
    private final DataStorageAPI dataStorage;
    private final Computation computation;

    public CoordinationEngine(DataStorageAPI dataStorage, Computation computation) {
        if (dataStorage == null || computation == null) {
            throw new IllegalArgumentException("DataStorage and Computation cannot be null");
        }
        this.dataStorage = dataStorage;
        this.computation = computation;
    }

    public String startComputation(String inputKey, String outputKey) {
        try {
            int[] inputData = dataStorage.readData(inputKey);
            if (inputData == null || inputData.length == 0) {
                return "Input data not found";
            }
            int number = Integer.parseInt(new String(inputData, 0, inputData.length));
            String result = computation.computeUserInput(number);
            dataStorage.writeData(outputKey, result.chars().toArray());
            return "Computation completed successfully";
        } catch (NumberFormatException e) {
            return "Invalid input data format";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
