package project.annotation;

import api.StorageComputeAPI;
import api.prototype.StorageComputeAPIPrototype;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestStorageComputeAPI {
    
    @Test
    public void testReadData() {
        // Mocking StorageComputeAPI
        StorageComputeAPI mockAPI = Mockito.mock(StorageComputeAPI.class);
        when(mockAPI.readData("testSource")).thenReturn("Mocked data");
        
        assertEquals("Mocked data", mockAPI.readData("testSource"));
    }
    
    @Test
    public void testWriteData() {
        // Mocking StorageComputeAPI
        StorageComputeAPI mockAPI = Mockito.mock(StorageComputeAPI.class);
        
        // Call the method
        mockAPI.writeData("testDestination", "testData");
        
        // Verify the method was called correctly
        verify(mockAPI).writeData("testDestination", "testData");
    }
}
