package project.apis.DataStorage;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import project.annotations.ProcessAPI; // Ensure annotation exists and is correctly imported

/**
 * Smoke test for DataStorageAPI 
 */
class DataStorageAPITest {

    @Mock
    private DataStorageAPI dataStorageAPI;

    /**
     * Initializes Mockito mocks before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the fetchData method.
     * Verifies that it returns the expected value when given a key.
     */
    @Test
    void testFetchData() {
        // Arrange
        String key = "testKey";
        String expectedValue = "testValue";
        when(dataStorageAPI.fetchData(key)).thenReturn(expectedValue);

        // Act
        String result = dataStorageAPI.fetchData(key);

        // Assert
        assertEquals(expectedValue, result, "fetchData should return the expected value.");
        verify(dataStorageAPI, times(1)).fetchData(key);
    }

    /**
     * Tests the storeData method.
     * Verifies that it is called once with the correct parameters.
     */
    @Test
    void testStoreData() {
        // Arrange
        String key = "testKey";
        String value = "testValue";

        // Act
        dataStorageAPI.storeData(key, value);

        // Assert
        verify(dataStorageAPI, times(1)).storeData(key, value);

        // Confirm no unexpected interactions
        verifyNoMoreInteractions(dataStorageAPI);
    }
}
