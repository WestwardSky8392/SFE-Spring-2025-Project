package project.annotations; // Likely correct package for production code

import java.util.List;

public class InMemoryOutputConfig implements OutputConfig {
    private List<String> outputList;

    public InMemoryOutputConfig(List<String> outputList) {
        this.outputList = outputList;
    }

    @Override
    public void writeOutput(String output) {
        outputList.add(output);
    }
}
