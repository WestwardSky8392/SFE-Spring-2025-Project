package project.annotations;

import java.util.List;

/**
 * Test-only implementation of the InputConfig interface.
 * Provides an in-memory list of integers for input.
 */
public class InMemoryInputConfig implements InputConfig {
    private final List<Integer> inputList;

    
    public InMemoryInputConfig(List<Integer> inputList) {
        this.inputList = inputList;
    }

    @Override
    public List<Integer> getInput() {
        return inputList;
    }

    @Override
    public String readInput() {
        // Implement if needed
        return null;
    }
}
