package project.apis.computeapi;

// Import necessary classes
import project.apis.computeapi.Computation;

public class SimpleCompute extends Computation {
    public SimpleCompute(String userInput) {
        super(userInput);
    }

    @Override
    public int[] processData(int[] inputData) {
        // A simple computation: multiply each element by 2
        int[] results = new int[inputData.length];
        for (int i = 0; i < inputData.length; i++) {
            results[i] = inputData[i] * 2;
        }
        return results;
    }
}
