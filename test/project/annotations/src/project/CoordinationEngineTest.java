import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CoordinationEngineTest {

    @Test
    public void testCoordinationEngineConstructorValidation() {
        // Mock DataStorageAPI and Computation
        DataStorageAPI mockDataStorage = new DataStorageAPI() {
            @Override
            public String fetchData(String key) {
                return "mockData";
            }

            @Override
            public void storeData(String key, String value) {
                // Mock store implementation
            }
        };
        Computation mockComputation = new Computation("mockInput");

        // Test: If we pass valid non-null parameters, it should succeed
        assertDoesNotThrow(() -> new CoordinationEngine(mockDataStorage, mockComputation), "Constructor should work with valid parameters.");

        // Test: If either parameter is null, it should throw an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new CoordinationEngine(null, mockComputation), "Constructor should throw IllegalArgumentException if DataStorageAPI is null.");
        assertThrows(IllegalArgumentException.class, () -> new CoordinationEngine(mockDataStorage, null), "Constructor should throw IllegalArgumentException if Computation is null.");
    }

    @Test
    public void testStartComputationValidation() {
        // Mock DataStorageAPI and Computation
        DataStorageAPI mockDataStorage = new DataStorageAPI() {
            @Override
            public String fetchData(String key) {
                return "mockData";
            }

            @Override
            public void storeData(String key, String value) {
                // Mock store implementation
            }
        };
        Computation mockComputation = new Computation("mockInput");
        CoordinationEngine engine = new CoordinationEngine(mockDataStorage, mockComputation);

        // Test: Should throw IllegalArgumentException if inputKey is null or empty
        assertThrows(IllegalArgumentException.class, () -> engine.startComputation(null, "outputKey"), "startComputation should throw IllegalArgumentException if inputKey is null.");
        assertThrows(IllegalArgumentException.class, () -> engine.startComputation("", "outputKey"), "startComputation should throw IllegalArgumentException if inputKey is empty.");

        // Test: Should throw IllegalArgumentException if outputKey is null or empty
        assertThrows(IllegalArgumentException.class, () -> engine.startComputation("inputKey", null), "startComputation should throw IllegalArgumentException if outputKey is null.");
        assertThrows(IllegalArgumentException.class, () -> engine.startComputation("inputKey", ""), "startComputation should throw IllegalArgumentException if outputKey is empty.");
    }
}
