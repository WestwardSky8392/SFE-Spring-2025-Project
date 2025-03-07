package project.apis.datastorage;

public interface DataStorageAPI {
    int[] readData(String location) throws Exception;
    void writeData(String location, int[] result) throws Exception;
}
