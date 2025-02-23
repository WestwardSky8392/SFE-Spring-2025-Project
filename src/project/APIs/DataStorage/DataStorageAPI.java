package project.APIs.DataStorage;

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
    String fetchData(String key);

    /**
     * Stores or updates a key-value pair in the data storage.
     *
     * @param key   The data key.
     * @param value The value to store.
     */
    void storeData(String key, String value);
}
