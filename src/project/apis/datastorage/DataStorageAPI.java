package project.apis.datastorage;

public interface DataStorageAPI {
    int[] readData(String key) throws Exception;
    void writeData(String key, int[] data) throws Exception;
}
