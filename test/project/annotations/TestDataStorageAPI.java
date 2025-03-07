package project.annotations;

import project.apis.datastorage.DataStorageAPI;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Smoke test for DataStorageAPI 
 */
class TestDataStorageAPI {

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
     * Tests the readData method.
     * Verifies that it returns the expected value when given a key.
     */
    @Test
    void testReadData() throws Exception {
        // Arrange
        String key = "testKey";
        int[] expectedValue = "12345".chars().toArray();  // Converts "12345" into an int[] of its code points.
        when(dataStorageAPI.readData(key)).thenReturn(expectedValue);

        // Act
        int[] result = dataStorageAPI.readData(key);

        // Assert: Convert both int arrays into Strings for an easy comparison.
        String expectedString = new String(expectedValue, 0, expectedValue.length);
        String resultString = new String(result, 0, result.length);
        assertEquals(expectedString, resultString);
        verify(dataStorageAPI, times(1)).readData(key);
    }

    /**
     * Tests the writeData method.
     * Verifies that it is called once with the correct parameters.
     */
    @Test
    void testWriteData() throws Exception {
        // Arrange
        String key = "testKey";
        int[] value = "12345".chars().toArray();

        // Act
        dataStorageAPI.writeData(key, value);

        // Assert: For void methods, verify that the method was invoked with the expected arguments.
        verify(dataStorageAPI, times(1)).writeData(key, value);

        // Confirm no unexpected interactions.
        verifyNoMoreInteractions(dataStorageAPI);
    }
}
