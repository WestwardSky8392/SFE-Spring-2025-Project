package project.annotations;

import java.util.List;

public class InMemoryDataStore implements DataStore {
    private final InputConfig inputConfig;
    private final OutputConfig outputConfig;

    public InMemoryDataStore(InputConfig inputConfig, OutputConfig outputConfig) {
        this.inputConfig = inputConfig;
        this.outputConfig = outputConfig;
    }

    @Override
    public void processData() {
        List<Integer> input = inputConfig.getInput();
        for (Integer num : input) {
            outputConfig.writeOutput("Processed: " + num);
        }
    }
}
