package project.annotations;

import project.apis.computeapi.Computation;

public class SimpleComputation extends Computation {
    @Override
    public String computeUserInput(int input) {
        // Simple digital root calculation
        int n = Math.abs(input);
        int steps = 0;
        while (n >= 10) {
            int sum = 0;
            int temp = n;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            n = sum;
            steps++;
        }
        return String.valueOf(steps);
    }

    @Override
    public int[] processData(int[] inputData) {
        // Just return the input for test purposes
        return inputData;
    }
}
