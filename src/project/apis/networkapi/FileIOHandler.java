import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class FileIOHandler {

    public List<String> readFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines().collect(Collectors.toList());
        }
    }

    public void writeFile(String filePath, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
