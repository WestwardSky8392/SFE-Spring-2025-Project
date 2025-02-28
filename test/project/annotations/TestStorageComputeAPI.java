package project.annotations;

import project.apis.datastorage.StorageComputeAPI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for StorageComputeAPI using Mockito.
 */
class TestStorageComputeAPI {

    /**
     * Tests the readData 
     */
    @Test
    void testReadData() {
        // Mock StorageComputeAPI
        StorageComputeAPI mockAPI = Mockito.mock(StorageComputeAPI.class);
        
        // Define behavior
        when(mockAPI.readData("testSource")).thenReturn("Mocked Data");

        // Verify behavior
        assertEquals("Mocked Data", mockAPI.readData("testSource"));
        verify(mockAPI).readData("testSource");
    }

    /**
     * Tests the writeData method 
     */
    @Test
    void testWriteData() {
        // Mock StorageComputeAPI
        StorageComputeAPI mockAPI = Mockito.mock(StorageComputeAPI.class);

        // Call method
        mockAPI.writeData("testDest", "Sample Data");

        // Verify interaction
        verify(mockAPI).writeData("testDest", "Sample Data");
    }
}
