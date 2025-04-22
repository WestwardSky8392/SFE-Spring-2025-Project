package project.apis.computeapi;

public class SimpleComputeSlow extends Computation {

    @Override
    public String computeUserInput(int input) {
        // Example computation: multiply the input by 2 and return as a string
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
