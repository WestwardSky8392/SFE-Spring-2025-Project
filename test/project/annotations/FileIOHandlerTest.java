import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FileIOHandlerTest {
    @Test
    public void testFileReadWrite() throws IOException {
        FileIOHandler fileIOHandler = new FileIOHandler();
        String testFilePath = "testFile.txt";
        List<String> linesToWrite = List.of("Line 1", "Line 2", "Line 3");

        fileIOHandler.writeFile(testFilePath, linesToWrite);
        List<String> readLines = fileIOHandler.readFile(testFilePath);

        assertEquals(linesToWrite, readLines);

        // Cleanup
        Files.delete(Path.of(testFilePath));
    }
}
