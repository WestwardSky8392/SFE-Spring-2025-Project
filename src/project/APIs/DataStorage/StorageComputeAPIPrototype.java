package api.prototype;

import api.StorageComputeAPI;
import annotations.ProcessAPIPrototype; 

/**
 * Prototype implementation of StorageComputeAPI for testing and simulation.
 */
public class StorageComputeAPIPrototype implements StorageComputeAPI {

    /**
     * Simulates reading data from a source.
     * 
     * @param source The source to read data from.
     * @return Simulated data.
     */
    @Override
    @ProcessAPIPrototype
    public String readData(String source) {
        return "Simulated data from " + source;
    }

    /**
     * Simulates writing data to a destination.
     * 
     * @param destination The location to store data.
     * @param data The data to be stored.
     */
    @Override
    @ProcessAPIPrototype
    public void writeData(String destination, String data) {
        System.out.println("Simulated storing data to " + destination + ": " + data);
    }
}
