package project.api;

import org.springframework.web.bind.annotation.*;
import project.apis.computeapi.FastDigitalRootPersistenceAPI;
import project.apis.computeapi.ImplementDigitalRootPersistenceAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DigitalRootController {

    private final ImplementDigitalRootPersistenceAPI originalApi = new ImplementDigitalRootPersistenceAPI();
    private final FastDigitalRootPersistenceAPI optimizedApi = new FastDigitalRootPersistenceAPI();

    @GetMapping("/calculate")
    public Map<String, Object> calculateSingle(@RequestParam("number") int number) {
        Map<String, Object> results = new HashMap<>();
        
        // Calculate using original implementation
        long originalStart = System.nanoTime();
        String originalResult = originalApi.processDigitalRootPersistence(number);
        long originalDuration = (System.nanoTime() - originalStart) / 1_000_000; // Convert to ms
        
        // Calculate using optimized implementation
        long optimizedStart = System.nanoTime();
        String optimizedResult = optimizedApi.processDigitalRootPersistence(number);
        long optimizedDuration = (System.nanoTime() - optimizedStart) / 1_000_000; // Convert to ms
        
        // Prepare results
        Map<String, Object> originalData = new HashMap<>();
        originalData.put("result", originalResult);
        originalData.put("time", originalDuration);
        
        Map<String, Object> optimizedData = new HashMap<>();
        optimizedData.put("result", optimizedResult);
        optimizedData.put("time", optimizedDuration);
        
        results.put("original", originalData);
        results.put("optimized", optimizedData);
        
        return results;
    }

    @PostMapping("/benchmark")
    public Map<String, Object> benchmark(@RequestBody BenchmarkRequest request) {
        Map<String, Object> results = new HashMap<>();
        
        // Generate input numbers
        List<Integer> inputs = new ArrayList<>();
        for (int i = 0; i < request.getCount(); i++) {
            inputs.add(request.getStartNumber() + i);
        }
        
        // Benchmark original implementation
        long originalStart = System.nanoTime();
        for (Integer input : inputs) {
            originalApi.processDigitalRootPersistence(input);
        }
        long originalTotalTime = (System.nanoTime() - originalStart) / 1_000_000; // Convert to ms
        
        // Benchmark optimized implementation
        long optimizedStart = System.nanoTime();
        for (Integer input : inputs) {
            optimizedApi.processDigitalRootPersistence(input);
        }
        long optimizedTotalTime = (System.nanoTime() - optimizedStart) / 1_000_000; // Convert to ms
        
        // Calculate improvement percentage
        double improvementPercent = (originalTotalTime - optimizedTotalTime) * 100.0 / originalTotalTime;
        
        // Prepare results
        Map<String, Object> originalData = new HashMap<>();
        originalData.put("totalTime", originalTotalTime);
        originalData.put("averageTime", (double) originalTotalTime / request.getCount());
        
        Map<String, Object> optimizedData = new HashMap<>();
        optimizedData.put("totalTime", optimizedTotalTime);
        optimizedData.put("averageTime", (double) optimizedTotalTime / request.getCount());
        
        results.put("original", originalData);
        results.put("optimized", optimizedData);
        results.put("improvementPercent", improvementPercent);
        
        return results;
    }

    // Request model for benchmark
    public static class BenchmarkRequest {
        private int startNumber;
        private int count;

        public int getStartNumber() {
            return startNumber;
        }

        public void setStartNumber(int startNumber) {
            this.startNumber = startNumber;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
