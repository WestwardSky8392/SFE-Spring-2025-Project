package project.apis.computeapi;

public abstract class Computation {
    public abstract String computeUserInput(int input);

    // Add processData method
    public abstract int[] processData(int[] inputData);
}
