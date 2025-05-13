package ina;

import java.util.List;
import java.util.ArrayList;

public class DigitalRootBatchJobManager {
    public List<Integer> computeBatchDigitalRoots(List<Integer> numbers) {
        List<Integer> results = new ArrayList<>();
        for (Integer n : numbers) {
            results.add(digitalRoot(n));
        }
        return results;
    }

    private int digitalRoot(int n) {
        while (n >= 10) {
            int sum = 0;
            while (n > 0) {
                sum += n % 10;
                n /= 10;
            }
            n = sum;
        }
        return n;
    }
}
