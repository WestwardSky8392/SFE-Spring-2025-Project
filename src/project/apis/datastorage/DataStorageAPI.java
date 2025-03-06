package project.apis.datastorage;

import project.annotations.ProcessAPI;

/**
 * Defines the API for storing and retrieving data using key-value pairs.
 */
@ProcessAPI
public interface DataStorageAPI {

    /**
     * Retrieves the value associated with the given key.
     *
     * @param key The unique identifier for the data.
     * @return The stored value, or {@code null} if not found.
     */
    default String fetchData(String key) {
        // Validate input parameter
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        // Implementation to fetch data
        return null; 
    }

    /**
     * Stores or updates a key-value pair in the data storage.
     *
     * @param key   The data key.
     * @param value The value to store.
     */
    default void storeData(String key, String value) {
        // Validate input parameters
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty");
        }
        // Implementation to store data
    }
}
