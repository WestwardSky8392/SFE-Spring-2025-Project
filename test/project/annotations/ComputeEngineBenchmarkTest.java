package project.annotations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import project.apis.computeapi.DigitalRootPersistenceAPI;
import project.apis.computeapi.ImplementDigitalRootPersistenceAPI;
import project.apis.computeapi.FastDigitalRootPersistenceAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * Benchmark integration test for DigitalRootPersistenceAPI 
 * at least 10% faster than the original
 */
public class ComputeEngineBenchmarkTest {

    // Helper to run the benchmark
    private long benchmark(DigitalRootPersistenceAPI api, List<Integer> inputs, int iterations) {
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            for (int n : inputs) {
                api.processDigitalRootPersistence(n);
            }
        }
        return System.nanoTime() - start;
    }

    @Test
    public void testOptimizedIsAtLeast10PercentFaster() {
        //large input set
        List<Integer> inputs = new ArrayList<>();
        for (int i = 100000; i < 101000; i++) {
            inputs.add(i);
        }
        int iterations = 100;

        DigitalRootPersistenceAPI original = new ImplementDigitalRootPersistenceAPI();
        DigitalRootPersistenceAPI fast = new FastDigitalRootPersistenceAPI();

        // JVM warmup
        benchmark(original, inputs, 2);
        benchmark(fast, inputs, 2);

        // Benchmark original
        long originalTime = benchmark(original, inputs, iterations);
        // Benchmark optimized
        long fastTime = benchmark(fast, inputs, iterations);

        System.out.println("Original: " + originalTime / 1_000_000 + " ms");
        System.out.println("Optimized: " + fastTime / 1_000_000 + " ms");

        // Assert that the optimized version is at least 10% faster
        assertTrue(fastTime < originalTime * 0.90,
            "Optimized version should be at least 10% faster than original. " +
            "Original: " + originalTime + " ns, Optimized: " + fastTime + " ns");
    }
}
