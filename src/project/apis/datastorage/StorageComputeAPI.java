package api;

import project.annotations.ProcessAPI; 

/**
 * StorageComputeAPI defines methods for reading and writing data 
 * between different storage locations.
 */
@ProcessAPI
public interface StorageComputeAPI {

    /**
     * Reads data from the specified source.
     * 
     * @param source The location to read data from.
     * @return The retrieved data as a string.
     */
    String readData(String source);

    /**
     * Writes data to the specified destination.
     * 
     * @param destination The location to store data.
     * @param data The data to be written.
     */
    void writeData(String destination, String data);
}
