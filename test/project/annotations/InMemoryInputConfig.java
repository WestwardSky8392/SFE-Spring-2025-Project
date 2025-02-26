package project.annotations;

import java.util.List;

/**
 * Test-only implementation of the InputConfig interface.
 * Provides an in-memory list of integers for input.
 */
public class InMemoryInputConfig implements InputConfig {  // Implementing the InputConfig interface
    private List<Integer> inputList;

    /**
     * Constructor to initialize the in-memory input list.
     * @param inputList The list of integers to use as input.
     */
    public InMemoryInputConfig(List<Integer> inputList) {
        this.inputList = inputList;
    }

    /**
     * Retrieves the in-memory input list.
     * @return The list of integers used for input.
     */
    @Override
    public List<Integer> getInput() {
        return inputList;
    }
}


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
