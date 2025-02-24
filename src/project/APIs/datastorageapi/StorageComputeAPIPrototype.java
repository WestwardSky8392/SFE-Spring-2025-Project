package datastorageapi;
public class StorageComputeAPIPrototype implements StorageComputeAPI {
    
    @Override
    public String readData(String source) {
        return "Simulated data from " + source;
    }

    @Override
    public void writeData(String destination, String data) {
        System.out.println("Simulated storing data to " + destination + ": " + data);
    }
}
