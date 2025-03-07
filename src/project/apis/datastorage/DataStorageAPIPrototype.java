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
    public int[] readData(String location) throws Exception {
        String data = storageComputeAPI.readData(location);
        // Convert the data to int[] as per the new interface
        return data.chars().toArray();
    }

    /*
      Stores data
    */
    @Override
    @ProcessAPIPrototype
    public void writeData(String location, int[] result) throws Exception {
        // Convert int[] to String for storage
        String data = new String(result, 0, result.length);
        storageComputeAPI.writeData(location, data);
    }
}
