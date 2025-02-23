package project.APIs.DataStorage;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataStorageAPI {
    String fetchData(String key);
    void storeData(String key, String value);
}
