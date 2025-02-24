package datastorageapi;
public interface StorageComputeAPI {
    String readData(String source);
    void writeData(String destination, String data);
}
