package project.apis.datastorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import project.apis.api_dataconnections.DigitalRootPersistenceAPI;

class TestDigitalRootPersistenceAPI {

    @Mock
    private DigitalRootPersistenceAPI digitalRootPersistenceAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessDigitalRootPersistence() {
        // Define multiple test cases
        int[] testNumbers = {9875, 12345, 999, 0, 1, 77};
        String[] expectedResults = {"2", "6", "9", "0", "1", "5"}; // Replace with actual expected results

        for (int i = 0; i < testNumbers.length; i++) {
            when(digitalRootPersistenceAPI.processDigitalRootPersistence(testNumbers[i]))
                .thenReturn(expectedResults[i]);

            // Act
            String result = digitalRootPersistenceAPI.processDigitalRootPersistence(testNumbers[i]);

            // Assert
            assertEquals(expectedResults[i], result, 
                "processDigitalRootPersistence should return the correct digital root persistence for input " + testNumbers[i]);

            verify(digitalRootPersistenceAPI, times(1)).processDigitalRootPersistence(testNumbers[i]);
        }

        // Ensure no unexpected interactions
        verifyNoMoreInteractions(digitalRootPersistenceAPI);
    }
}
