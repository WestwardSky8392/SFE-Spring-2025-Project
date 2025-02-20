package src;
import api.StorageComputeAPI;

public class StorageComputeAPIImpl implements StorageComputeAPI {
    
   
    private String inputSource;
    private String outputDestination;

    // Constructor
    public StorageComputeAPIImpl(String inputSource, String outputDestination) {
        this.inputSource = inputSource;
        this.outputDestination = outputDestination;
    }

    @Override
    public String readData(String source) {
        // Placeholder implementation, returning a default failure value
        return "";  
    }

    @Override
    public void writeData(String destination, String data) {
        // Placeholder implementation, nothing for now
    }
}
