package test.project.annotations;

import java.util.List;

/**
 * Test-only implementation of the OutputConfig interface.
 * Provides an in-memory list of strings to write output to.
 */
public class InMemoryOutputConfig implements OutputConfig {  // Implementing the OutputConfig interface
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

    // Unit Test for InMemoryOutputConfig
    public static class InMemoryOutputConfigTest {

        private InMemoryOutputConfig inMemoryOutputConfig;
        private List<String> mockOutputList;
        private OutputConfig mockOutputConfig;

        @BeforeEach
        void setUp() {
            mockOutputList = new ArrayList<>();
            mockOutputConfig = mock(OutputConfig.class); // Mock the OutputConfig interface
            inMemoryOutputConfig = new InMemoryOutputConfig(mockOutputList);
        }

        @Test
        void testWriteOutput() {
            // Simulate writing output
            String output = "Test Output";
            inMemoryOutputConfig.writeOutput(output);

            // Verify that output is written to the list
            assert mockOutputList.contains(output) : "Output should be added to the list";
        }

        @Test
        void testMockWriteOutput() {
            // Using Mockito to verify the interaction
            mockOutputConfig.writeOutput("Mocked Output");

            // Verify that writeOutput was called on the mock
            verify(mockOutputConfig).writeOutput("Mocked Output");
        }
    }
}
