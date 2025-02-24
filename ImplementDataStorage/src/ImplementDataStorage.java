import api.datastorage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ImplementDataStorage {
    @Test
    public void testFetchData() {
        // Create mock dependencies
        datastorage mockDataStore = mock(ImplementDataStorage.class);

        // Instantiate the class with the mocked dependency
        ImplementDataStorage api1 = new ImplementDataStorage(mockDataStore);

        // Call the method
        String result = api1.fetchData();

        // Verify the result is the expected default (empty string)
        assertEquals("", result);
    }

    @Test
    public void testProcessData() {
        datastorage mockDataStore = mock(ImplementDataStorage.class);
        ImplementDataStorage api1 = new ImplementDataStorage(mockDataStore);

        int result = ImplementDataStorage.processData("some data");

        // Verify the result is the expected default failure value (-1)
        assertEquals(-1, result);
    }

}
