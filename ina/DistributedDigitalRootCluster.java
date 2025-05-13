package ina;

import java.util.List;
import java.util.concurrent.*;

public class DistributedDigitalRootCluster {
    private final ExecutorService executor;

    public DistributedDigitalRootCluster(int nodeCount) {
        this.executor = Executors.newFixedThreadPool(nodeCount);
    }

    public List<Future<Integer>> computeDistributedDigitalRoots(List<Integer> numbers) {
        List<Future<Integer>> futures = new ArrayList<>();
        for (Integer n : numbers) {
            futures.add(executor.submit(() -> digitalRoot(n)));
        }
        return futures;
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

    public void shutdown() {
        executor.shutdown();
    }
}
