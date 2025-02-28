package project.annotations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import project.apis.computeapi.Computation;
import project.apis.computeapi.CoordinationEngine;
import project.apis.datastorage.DataStorageAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Tests the CoordinationEngine 
 */
public class TestCoordinationEngine {

    private DataStorageAPI dataStorage;
    private Computation computation;
    private CoordinationEngine engine;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        dataStorage = Mockito.mock(DataStorageAPI.class);
        computation = Mockito.mock(Computation.class);
        engine = new CoordinationEngine(dataStorage, computation);
    }

    /**
     * Tests the startComputation method with valid input data.
     * Verifies that the computation completes successfully and the result is stored correctly.
     */
    @Test
    public void testStartComputationWithValidData() {
        when(dataStorage.fetchData("inputKey")).thenReturn("12345");
        when(computation.computeUserInput(12345)).thenReturn("6");
        
        String result = engine.startComputation("inputKey", "outputKey");
        assertEquals("Computation completed successfully", result);
        
        // Capture the arguments passed to storeData
        ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> valueCaptor = ArgumentCaptor.forClass(String.class);
        verify(dataStorage).storeData(keyCaptor.capture(), valueCaptor.capture());
        
        // Print the actual arguments for debugging
        System.out.println("Key: " + keyCaptor.getValue());
        System.out.println("Value: " + valueCaptor.getValue());
        
        // Ensure the arguments match the expected values
        assertEquals("outputKey", keyCaptor.getValue());
        assertEquals("6", valueCaptor.getValue());
    }

    /**
     * Tests the startComputation method with invalid input data.
     * Verifies that the method handles invalid input data format correctly.
     */
    @Test
    public void testStartComputationWithInvalidData() {
        when(dataStorage.fetchData("inputKey")).thenReturn("invalid");
        String result = engine.startComputation("inputKey", "outputKey");
        assertEquals("Invalid input data format", result);
    }

    /**
     * Tests the startComputation method with missing input data.
     * Verifies that the method handles missing input data correctly.
     */
    @Test
    public void testStartComputationWithMissingData() {
        when(dataStorage.fetchData("inputKey")).thenReturn(null);
        String result = engine.startComputation("inputKey", "outputKey");
        assertEquals("Input data not found", result);
    }
}
