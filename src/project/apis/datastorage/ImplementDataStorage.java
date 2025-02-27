package project.apis.datastorage;

import project.apis.datastorage.StorageComputeAPI;
import project.apis.datastorage.DataStorageAPI; 
import project.annotations.ProcessAPIPrototype;

/**
 * Implementation of the StorageComputeAPI.
 * This class provides functionality to read and write data using a DataStorageAPI.
 */
public class ImplementDataStorage implements StorageComputeAPI {
    
    // DataStorageAPI reference 
    private DataStorageAPI dataStorageAPI; 

    /**
     * Default constructor for test purposes.
     * Initializes with a new ImplementDataStorageAPI instance.
     */
    public ImplementDataStorage() {
        this.dataStorageAPI = new ImplementDataStorageAPI();
    }

    /**
     * Constructor to initialize dependencies.
     * @param dataStorageAPI The data storage API that this component interacts with.
     */
    public ImplementDataStorage(DataStorageAPI dataStorageAPI) {
        this.dataStorageAPI = dataStorageAPI;
    }

    /**
     * Reads data from the given source using the dataStorageAPI.
     * @param source The input source identifier.
     * @return The data read from the source, or an empty string if not found.
     */
    @Override
    @ProcessAPIPrototype
    public String readData(String source) {
        if (dataStorageAPI == null) {
            return ""; // Return empty string if dataStorageAPI is not initialized
        }
        String data = dataStorageAPI.fetchData(source);
        return data != null ? data : ""; // Return empty string if data is null
    }

    /**
     * Writes data to the given destination using the dataStorageAPI.
     * @param destination The output destination identifier.
     * @param data The data to write.
     */
    @Override
    @ProcessAPIPrototype
    public void writeData(String destination, String data) {
        if (dataStorageAPI != null) {
            dataStorageAPI.storeData(destination, data);
        }
    }
}
