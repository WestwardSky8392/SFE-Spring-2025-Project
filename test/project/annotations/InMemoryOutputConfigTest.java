package test.project.annotations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

public class InMemoryOutputConfigTest {
    private InMemoryOutputConfig inMemoryOutputConfig;
    private ArrayList<String> mockOutputList; 
    private OutputConfig mockOutputConfig;

    @BeforeEach
    void setUp() {
        mockOutputList = new ArrayList<>();
        mockOutputConfig = mock(OutputConfig.class);
        inMemoryOutputConfig = new InMemoryOutputConfig(mockOutputList);
    }

    @Test
    void testWriteOutput() {
        String output = "Test Output";
        inMemoryOutputConfig.writeOutput(output);
        assert mockOutputList.contains(output) : "Output should be added to the list";
    }

    @Test
    void testMockWriteOutput() {
        mockOutputConfig.writeOutput("Mocked Output");
        verify(mockOutputConfig).writeOutput("Mocked Output");
    }
}
