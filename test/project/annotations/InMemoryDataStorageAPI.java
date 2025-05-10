package project.annotations;

import project.apis.datastorage.DataStorageAPI;

public class InMemoryDataStorageAPI implements DataStorageAPI {
    private final java.util.Map<String, int[]> storage = new java.util.HashMap<>();

    @Override
    public int[] readData(String key) {
        return storage.getOrDefault(key, new int[0]);
    }

    @Override
    public void writeData(String key, int[] data) {
        storage.put(key, data);
    }
}
