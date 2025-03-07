package project.apis.datastorage;

import project.apis.datastorage.StorageComputeAPI;
import project.apis.datastorage.DataStorageAPI; 
import project.annotations.ProcessAPIPrototype;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        try {
            int[] data = dataStorageAPI.readData(source);
            return new String(data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
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
            try {
                dataStorageAPI.writeData(destination, data.chars().toArray());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads data from the given file path.
     * @param filePath The file path to read data from.
     * @return The data read from the file, or an empty string if an error occurs.
     */
    public String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Writes data to the given file path.
     * @param filePath The file path to write data to.
     * @param data The data to write.
     */
    public void writeFile(String filePath, String data) {
        try {
            Files.write(Paths.get(filePath), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
