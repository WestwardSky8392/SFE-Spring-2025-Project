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
    public void testStartComputationWithValidData() throws Exception {
        // Stub readData to return int[] corresponding to "12345"
        when(dataStorage.readData("inputKey")).thenReturn("12345".chars().toArray());
        // Stub computation to return "6" when passed 12345 as integer
        when(computation.computeUserInput(12345)).thenReturn("6");
        
        String result = engine.startComputation("inputKey", "outputKey");
        assertEquals("Computation completed successfully", result);
        
        // Capture the arguments passed to writeData
        ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<int[]> valueCaptor = ArgumentCaptor.forClass(int[].class);
        verify(dataStorage).writeData(keyCaptor.capture(), valueCaptor.capture());
        
        // For debugging: print captured values
        System.out.println("Key: " + keyCaptor.getValue());
        String capturedValue = new String(valueCaptor.getValue(), 0, valueCaptor.getValue().length);
        System.out.println("Value: " + capturedValue);
        
        // Ensure the captured arguments match the expected values
        assertEquals("outputKey", keyCaptor.getValue());
        assertEquals("6", capturedValue);
    }

    /**
     * Tests the startComputation method with invalid input data.
     * Verifies that the method handles invalid input data format correctly.
     */
    @Test
    public void testStartComputationWithInvalidData() throws Exception {
        // Stub readData to return int[] corresponding to "invalid"
        when(dataStorage.readData("inputKey")).thenReturn("invalid".chars().toArray());
        
        String result = engine.startComputation("inputKey", "outputKey");
        assertEquals("Invalid input data format", result);
    }

    /**
     * Tests the startComputation method with missing input data.
     * Verifies that the method handles missing input data correctly.
     */
    @Test
    public void testStartComputationWithMissingData() throws Exception {
        // Stub readData to return null
        when(dataStorage.readData("inputKey")).thenReturn(null);
        
        String result = engine.startComputation("inputKey", "outputKey");
        assertEquals("Input data not found", result);
    }
}
