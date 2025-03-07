package project.apis.datastorage;

import project.annotations.ProcessAPIPrototype;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the DataStorageAPI interface.
 * This class provides a simple in-memory key-value store.
 */
public class ImplementDataStorageAPI implements DataStorageAPI {
    
    // In-memory storage using a HashMap
    private final Map<String, String> dataStore;
    
    /**
     * Default constructor that initializes the data store.
     */
    public ImplementDataStorageAPI() {
        this.dataStore = new HashMap<>();
        
        // Initialize with some test data
        dataStore.put("testKey", "testValue");
    }
    
    /**
     * Retrieves the value associated with the given key.
     *
     * @param key The unique identifier for the data.
     * @return The stored value, or {@code null} if not found.
     */
    @Override
    @ProcessAPIPrototype
    public int[] readData(String location) {
        String data = dataStore.get(location);
        return data != null ? data.chars().toArray() : new int[0];
    }
    
    /**
     * Stores or updates a key-value pair in the data storage.
     *
     * @param key   The data key.
     * @param value The value to store.
     */
    @Override
    @ProcessAPIPrototype
    public void writeData(String location, int[] result) {
        String data = new String(result, 0, result.length);
        dataStore.put(location, data);
    }
}
