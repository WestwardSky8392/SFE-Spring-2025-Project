package project.apis.datastorage;

import project.annotations.ProcessAPIPrototype;

public class DataStorageAPIPrototype implements DataStorageAPI {

    private final StorageComputeAPI storageComputeAPI;

    /*
       Initialize
    */
    public DataStorageAPIPrototype(StorageComputeAPI storageComputeAPI) {
        this.storageComputeAPI = storageComputeAPI;
    }

    /*
      Fetches data
    */
    @Override
    @ProcessAPIPrototype
    public String fetchData(String key) {
        return storageComputeAPI.readData(key);
    }

    /*
      Stores data
    */
    @Override
    @ProcessAPIPrototype
    public void storeData(String key, String value) {
        storageComputeAPI.writeData(key, value);
    }
}
