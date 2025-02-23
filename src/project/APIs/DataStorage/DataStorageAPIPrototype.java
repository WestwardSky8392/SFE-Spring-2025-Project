package project.APIs.DataStorage;

import project.APIs.DataStorage.StorageComputeAPI;
import project.annotations.ProcessAPIPrototype;

public class DataStorageAPIPrototype implements DataStorageAPI {

    private final StorageComputeAPI storageComputeAPI;

    // Constructor to initialize the StorageComputeAPI
    public DataStorageAPIPrototype(StorageComputeAPI storageComputeAPI) {
        this.storageComputeAPI = storageComputeAPI;
    }

    @Override
    @ProcessAPIPrototype
    public String fetchData(String key) {
        // Placeholder response 
        return storageComputeAPI.readData(key);
    }

    @Override
    @ProcessAPIPrototype
    public void storeData(String key, String value) {
        // Placeholder action 
        storageComputeAPI.writeData(key, value);
    }
}
