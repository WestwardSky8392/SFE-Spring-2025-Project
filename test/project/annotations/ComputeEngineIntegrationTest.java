import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import project.apis.computeapi.ImplementDigitalRootPersistenceAPI;
import project.apis.computeapi.DigitalRootPersistenceAPI;
import project.annotations.InputConfig;
import project.annotations.InMemoryInputConfig;
import project.annotations.DataStore;
import project.annotations.OutputConfig;
import project.annotations.InMemoryDataStore;

public class ComputeEngineIntegrationTest {

    /**
     * Implementation of OutputConfig 
     */
    private static class TestOutputConfig implements OutputConfig {
        private List<String> outputs = new java.util.ArrayList<>();

        @Override
        public void writeOutput(String data) {
            outputs.add(data);
        }

        public List<String> getOutputs() {
            return outputs;
        }
    }

    @Test
    public void integrationTest() {
        // Arrange: create the test input using InMemoryInputConfig.
        List<Integer> inputList = Arrays.asList(1, 10, 25);
        InputConfig inputConfig = new InMemoryInputConfig(inputList);
        TestOutputConfig outputConfig = new TestOutputConfig();

        // Set up the test-only data store with our input and output configurations.
        DataStore dataStore = new InMemoryDataStore(inputConfig, outputConfig);

        // Initialize the compute engine components.
        // DigitalRootPersistenceAPI processes individual numbers.
        DigitalRootPersistenceAPI digitalRootEngine = new ImplementDigitalRootPersistenceAPI();

        // and write the result to the output.
        for (Integer num : inputList) {
            String result = digitalRootEngine.processDigitalRootPersistence(num);
            outputConfig.writeOutput(result);
        }

        List<String> expectedOutputs = Arrays.asList(
            "Processing not yet implemented.",
            "Processing not yet implemented.",
            "Processing not yet implemented."
        );
        assertEquals(expectedOutputs, outputConfig.getOutputs(),
                "The output should match the expected placeholder results.");
    }
}
