package project.apis.datastorage;

import java.util.HashMap;
import java.util.Map;

public class SimpleDataStorage implements DataStorageAPI {
    private final Map<String, int[]> storage = new HashMap<>();

    @Override
    public int[] readData(String key) throws Exception {
        if (!storage.containsKey(key)) {
            throw new Exception("Data not found for key: " + key);
        }
        return storage.get(key);
    }

    @Override
    public void writeData(String key, int[] data) throws Exception {
        storage.put(key, data);
    }
}
