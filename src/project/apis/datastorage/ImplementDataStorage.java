package project.apis.datastorage;

/**
 * Prototype implementation of the StorageComputeAPI.
 * This is a placeholder implementation for testing and structure purposes.
 * Currently, it does not perform any real data processing.
 */
public class ImplementDataStorage implements StorageComputeAPI {
    
    // DataStorageAPI reference 
    private DataStorageAPI dataStorageAPI; 

    /**
     * Default constructor for test purposes.
     */
    public ImplementDataStorage() {
        this.dataStorageAPI = null; // Placeholder
    }

    /**
     * Constructor to initialize dependencies.
     * @param dataStorageAPI The data storage API that this component interacts with.
     */
    public ImplementDataStorage(DataStorageAPI dataStorageAPI) {
        this.dataStorageAPI = dataStorageAPI;
    }

    /**
     * Reads data from the given source.
     * @param source The input source identifier.
     * @return An empty string as a placeholder.
     */
    @Override
    @ProcessAPIPrototype
    public String readData(String source) {
        return ""; // Placeholder implementation
    }

    /**
     * Writes data to the given destination.
     * @param destination The output destination identifier.
     * @param data The data to write.
     */
    @Override
    @ProcessAPIPrototype
    public void writeData(String destination, String data) {
        // Empty implementation as per task requirements
    }
}
