package test.project.annotations;

import java.util.List;

/**
 * Test-only implementation of the DataStore interface.
 * Processes input data and writes results to output.
 */
public class InMemoryDataStore implements DataStore {
    private InputConfig inputConfig;  // Using the InputConfig interface
    private OutputConfig outputConfig;  // Using the OutputConfig interface

    /**
     * Constructor to initialize the DataStore with input and output configurations.
     * @param inputConfig The configuration containing the input data.
     * @param outputConfig The configuration where the output will be written.
     */
    public InMemoryDataStore(InputConfig inputConfig, OutputConfig outputConfig) {
        this.inputConfig = inputConfig;
        this.outputConfig = outputConfig;
    }

    /**
     * Processes the input data and writes the results to the output configuration.
     * In this example, converts integers to strings.
     */
    @Override
    public void processData() {
        List<Integer> input = inputConfig.getInput();
        
        // Example processing: convert integers to strings and write to output
        for (Integer num : input) {
            outputConfig.writeOutput("Processed: " + num);
        }
    }
}
