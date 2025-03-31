package project.apis.computeapi;

public class SimpleCompute extends Computation {

    public SimpleCompute() {
        // No arguments needed since the parent class has no constructor with arguments
    }

    @Override
    public String computeUserInput(int input) {
        // Example computation: multiply the input by 2 and return as a string
        return String.valueOf(input * 2);
    }

    @Override
    public int[] processData(int[] inputData) {
        // Example computation: multiply each element by 2
        int[] results = new int[inputData.length];
        for (int i = 0; i < inputData.length; i++) {
            results[i] = inputData[i] * 2;
        }
        return results;
    }
}
