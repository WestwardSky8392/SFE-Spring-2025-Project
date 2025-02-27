public class SimpleCompute implements Compute {
    @Override
    public int[] processData(int[] inputData) {
        // A simple computation: multiply each element by 2
        int[] results = new int[inputData.length];
        for (int i = 0; i < inputData.length; i=i+1) {
            results[i] = inputData[i] * 2;
        }
        return results;
    }
}
