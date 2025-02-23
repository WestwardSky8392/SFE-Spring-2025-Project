package datastorageapi;

import api.StorageComputeAPI;
import annotations.ProcessAPIPrototype; 

public class StorageComputeAPIPrototype implements StorageComputeAPI {
    
    @Override
    @ProcessAPIPrototype
    public String readData(String source) {
        return "Simulated data from " + source;
    }

    @Override
    @ProcessAPIPrototype
    public void writeData(String destination, String data) {
        System.out.println("Simulated storing data to " + destination + ": " + data);
    }
}
