package test.project.annotations;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test-only implementation of the OutputConfig interface.

 */
public class InMemoryOutputConfig {

    // Mock OutputConfig interface
    public interface OutputConfig {
        void writeOutput(String output);
    }

    private List<String> outputList;

    /**
     * @param outputList The list to store output strings.
     */
    public InMemoryOutputConfig(List<String> outputList) {
        this.outputList = outputList;
    }

    /**
     * Writes a string to the in-memory output list
     */
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
