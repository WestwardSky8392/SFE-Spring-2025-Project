package project.api;

import project.annotations.ProcessAPI;
import project.annotations.ProcessAPIPrototype;

/**
 * API for interaction between the data storage system and the compute engine.
 * Handles reading from and writing to storage locations.
 */
@ProcessAPI
public interface DataStorageAPI {

    /**
     * Reads data from a given input source.
     *
     * @param source The input source identifier (e.g., file path, URL)
     * @return The data read from the source.
     */
    String readData(String source);

    /**
     * Writes computed results to an output destination.
     *
     * @param destination The output destination identifier.
     * @param data The processed data to be written.
     * @return true if the operation succeeds, false otherwise.
     */
    boolean writeData(String destination, String data);
}

/**
 * implementing DataStorageAPI.
 */
class DataStorageAPIPrototype implements DataStorageAPI {

    @ProcessAPIPrototy
    @Override
    public String readData(String source) {
        // Simulate reading data from a source
        return "Sample data from " + source;
    }

    @ProcessAPIPrototy
    @Override
    public boolean writeData(String destination, String data) {
        // Simulate writing data to a destination
        System.out.println("Writing data to " + destination + ": " + data);
        return true; // Simulated success
    }
}
