package test.project.annotations;

/**
 * Test-only implementation of the OutputConfig interface.
 * Provides an in-memory list of strings to write output to.
 */
public class InMemoryOutputConfig implements OutputConfig {
    private List<String> outputList;

    /**
     * Constructor to initialize the in-memory output list.
     * @param outputList The list to store output strings.
     */
    public InMemoryOutputConfig(List<String> outputList) {
        this.outputList = outputList;
    }

    /**
     * Writes a string to the in-memory output list.
     * @param output The output string to be added.
     */
    @Override
    public void writeOutput(String output) {
        outputList.add(output);
    }
}
