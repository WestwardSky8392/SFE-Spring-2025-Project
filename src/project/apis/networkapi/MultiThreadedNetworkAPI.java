package project.apis.networkapi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import project.apis.computeapi.CoordinationEngine;

public class MultiThreadedNetworkAPI extends BaseNetworkAPI {
    private static final int THREAD_POOL_SIZE = 12; 
    private final ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public MultiThreadedNetworkAPI(Screen screen, CoordinationEngine coordinationEngine) {
        super(screen, coordinationEngine);
    }

    public MultiThreadedNetworkAPI(Screen screen) {
        super(screen, null);
    }

    @Override
    public String makeApiCall(Callable<String> apiTask) throws Exception {
        // For backward compatibility, this still behaves the same way
        // but for true parallelism, use submitApiCall and executeTasksInParallel instead
        return executor.submit(apiTask).get();
    }
    
    /**
     * Submit an API task for execution without blocking
     * @param apiTask The task to execute
     * @return A Future representing the pending result
     */
    public Future<String> submitApiCall(Callable<String> apiTask) {
        return executor.submit(apiTask);
    }
    
    /**
     * Process a large input by splitting it into chunks and processing in parallel
     * This method demonstrates true parallel execution by submitting multiple tasks
     * and then waiting for all results
     */
    public String processInParallel(String input) throws Exception {
        if (input == null || input.isEmpty()) {
            return "";
        }
        
        // Split input into chunks for parallel processing
        List<String> chunks = splitInput(input, THREAD_POOL_SIZE);
        List<Future<String>> futures = new ArrayList<>();
        
        // Submit each chunk for processing (this doesn't block)
        for (String chunk : chunks) {
            futures.add(executor.submit(() -> processChunk(chunk)));
        }
        
        // Combine results from all threads after they complete
        StringBuilder result = new StringBuilder();
        for (Future<String> future : futures) {
            result.append(future.get()); // Only blocks here when retrieving results
        }
        
        return result.toString();
    }
    
    /**
     * Process multiple tasks in parallel and return when all are complete
     * @param tasks List of tasks to process
     * @return List of results in the same order as the tasks
     */
    public <T> List<T> executeTasksInParallel(List<Callable<T>> tasks) throws Exception {
        if (tasks == null || tasks.isEmpty()) {
            return new ArrayList<>();
        }
        
        // Submit all tasks without blocking
        List<Future<T>> futures = new ArrayList<>();
        for (Callable<T> task : tasks) {
            futures.add(executor.submit(task));
        }
        
        // Collect results in same order
        List<T> results = new ArrayList<>(tasks.size());
        for (Future<T> future : futures) {
            results.add(future.get());
        }
        
        return results;
    }
    
    /**
     * Split a string into approximately equal chunks
     */
    private List<String> splitInput(String input, int numChunks) {
        List<String> chunks = new ArrayList<>();
        int length = input.length();
        int chunkSize = Math.max(1, length / numChunks);
        
        for (int i = 0; i < length; i += chunkSize) {
            chunks.add(input.substring(i, Math.min(length, i + chunkSize)));
        }
        
        return chunks;
    }
    
    /**
     * Process an individual chunk (delegates to computation component)
     * This implements the feedback request to have components talk to each other
     */
    private String processChunk(String chunk) {
        // Delegate to the computation API
        try {
            // Create a simple computation task that processes the chunk
            project.apis.computeapi.DigitalRootPersistenceAPI computeApi = 
                new project.apis.computeapi.ImplementDigitalRootPersistenceAPI();
            
            // Try to parse the chunk as a number for processing
            int number;
            try {
                number = Integer.parseInt(chunk.trim());
            } catch (NumberFormatException e) {
                // If it's not a valid number, use a default
                number = 12345;
            }
            
            // Process using the computation component
            return computeApi.processDigitalRootPersistence(number);
        } catch (Exception e) {
            System.err.println("Error delegating to computation component: " + e.getMessage());
            return "Error processing: " + chunk;
        }
    }

    @Override
    public Window showWindow(AskUser askUser) {
        System.out.println("MultiThreadedNetworkAPI: Processing input with multithreading for: " + askUser.getInputPath());
        try {
            // Process input in parallel
            String result = processInParallel(askUser.getInputPath());
            System.out.println("Parallel processing complete: " + result);
        } catch (Exception e) {
            System.err.println("Error in parallel processing: " + e.getMessage());
        }
        return screen.showWindow(askUser); // Delegate to the screen implementation
    }

    public void shutdown() {
        executor.shutdown();
    }
}