package project.annotations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import project.apis.computeapi.DigitalRootPersistenceAPI;
import project.apis.computeapi.ImplementDigitalRootPersistenceAPI;
import project.apis.computeapi.FastDigitalRootPersistenceAPI;
import project.apis.datastorage.SimpleDataStorage;
import project.apis.computeapi.CoordinationEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark integration test for DigitalRootPersistenceAPI implementations.
 * Verifies that the optimized version is at least 10% faster than the original.
 */
public class ComputeEngineBenchmarkTest {

    // Helper to run the benchmark for direct API comparison
    private long benchmarkDirectAPI(DigitalRootPersistenceAPI api, List<Integer> inputs, int iterations) {
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            for (int n : inputs) {
                api.processDigitalRootPersistence(n);
            }
        }
        return System.nanoTime() - start;
    }
    
    // Helper to run the benchmark via the coordination engine
    private long benchmarkViaCoordinationEngine(DigitalRootPersistenceAPI api, List<Integer> inputs, 
                                              String inputKey, String outputKey) throws Exception {
        // Create a simple data storage
        SimpleDataStorage dataStorage = new SimpleDataStorage();
        
        // Store the input data
        StringBuilder inputStr = new StringBuilder();
        for (int n : inputs) {
            inputStr.append(n).append(",");
        }
        dataStorage.writeData(inputKey, inputStr.toString().getBytes());
        
        // Create a compute wrapper for the API
        Computation computation = number -> {
            return api.processDigitalRootPersistence(number);
        };
        
        // Create a coordination engine with the given components
        CoordinationEngine engine = new CoordinationEngine(dataStorage, computation);
        
        // Measure execution time
        long start = System.nanoTime();
        engine.startComputation(inputKey, outputKey);
        return System.nanoTime() - start;
    }

    @Test
    public void testDirectApiOptimizedIsAtLeast10PercentFaster() {
        // Prepare a large input set with big numbers to make computation CPU-intensive
        List<Integer> inputs = new ArrayList<>();
        for (int i = 10_000_000; i < 10_001_000; i++) {
            inputs.add(i);
        }
        int iterations = 5;  // Reduced iterations but with much larger inputs

        DigitalRootPersistenceAPI original = new ImplementDigitalRootPersistenceAPI();
        DigitalRootPersistenceAPI fast = new FastDigitalRootPersistenceAPI();

        // Warm up JVM to avoid bias from JIT compilation
        for (int i = 0; i < 3; i++) {
            benchmarkDirectAPI(original, inputs.subList(0, 10), 1);
            benchmarkDirectAPI(fast, inputs.subList(0, 10), 1);
        }

        // Benchmark original
        long originalTime = benchmarkDirectAPI(original, inputs, iterations);
        // Benchmark optimized
        long fastTime = benchmarkDirectAPI(fast, inputs, iterations);

        // Convert to milliseconds for readability
        double originalMs = originalTime / 1_000_000.0;
        double fastMs = fastTime / 1_000_000.0;
        
        System.out.println("Direct API Benchmark:");
        System.out.println("Original: " + originalMs + " ms");
        System.out.println("Optimized: " + fastMs + " ms");
        System.out.println("Improvement: " + (100.0 * (originalTime - fastTime) / originalTime) + "%");

        // Assert that the optimized version is at least 10% faster
        assertTrue(fastTime < originalTime * 0.90,
            "Optimized version should be at least 10% faster than original. " +
            "Original: " + originalMs + " ms, Optimized: " + fastMs + " ms");
    }
    
    @Test
    public void testCoordinationEngineOptimizedIsAtLeast10PercentFaster() throws Exception {
        // Create input data for testing
        List<Integer> inputs = new ArrayList<>();
        for (int i = 10_000_000; i < 10_000_100; i++) {
            inputs.add(i);
        }
        
        // Initialize APIs
        DigitalRootPersistenceAPI original = new ImplementDigitalRootPersistenceAPI();
        DigitalRootPersistenceAPI fast = new FastDigitalRootPersistenceAPI();
        
        // Define keys
        String inputKey = "benchmark-input";
        String outputKeyOriginal = "benchmark-output-original";
        String outputKeyFast = "benchmark-output-fast";
        
        // Warm up to avoid JIT compilation bias
        benchmarkViaCoordinationEngine(original, inputs.subList(0, 5), inputKey, outputKeyOriginal);
        benchmarkViaCoordinationEngine(fast, inputs.subList(0, 5), inputKey, outputKeyFast);
        
        // Benchmark original implementation with engine
        long originalTime = benchmarkViaCoordinationEngine(original, inputs, inputKey, outputKeyOriginal);
        
        // Benchmark optimized implementation with engine
        long fastTime = benchmarkViaCoordinationEngine(fast, inputs, inputKey, outputKeyFast);
        
        // Convert to milliseconds for readability
        double originalMs = originalTime / 1_000_000.0;
        double fastMs = fastTime / 1_000_000.0;
        
        System.out.println("\nCoordination Engine Benchmark:");
        System.out.println("Original: " + originalMs + " ms");
        System.out.println("Optimized: " + fastMs + " ms");
        System.out.println("Improvement: " + (100.0 * (originalTime - fastTime) / originalTime) + "%");
        
        // Assert that the optimized version is at least 10% faster
        assertTrue(fastTime < originalTime * 0.90,
            "Optimized engine should be at least 10% faster than original. " +
            "Original: " + originalMs + " ms, Optimized: " + fastMs + " ms");
    }
}
