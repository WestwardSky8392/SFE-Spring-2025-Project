import project.apis.datastorage.StorageComputeAPI;
import project.apis.datastorage.DataStorageAPI;
import project.annotations.ProcessAPIPrototype;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 
 * This implementation is used for unit testing purposes.
 * project note: Should pass unit test and be able to read files now.
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
     * Reads data from the given source file.
     * @param source The input source file path.
     * @return The content of the file as a string.
     */
    @Override
    @ProcessAPIPrototype
    public String readData(String source) {
        try {
            return new String(Files.readAllBytes(Paths.get(source)));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Writes data to the given destination file.
     * @param destination The output destination file path.
     * @param data The data to write.
     */
    @Override
    @ProcessAPIPrototype
    public void writeData(String destination, String data) {
        try {
            Files.write(Paths.get(destination), data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches data associated with the given key.
     * @param key The key to fetch data for.
     * @return The associated data as a string.
     */
    public String fetchData(String key) {
        // Implement logic to fetch data based on the key
        return ""; // placeholder
    }

    /**
     * Stores data with the given key.
     * @param key The key to store data under.
     * @param value The data to store.
     */
    public void storeData(String key, String value) {
        //  logic to store data based on the key, not here for now, not meant for unit testing
    }
}
