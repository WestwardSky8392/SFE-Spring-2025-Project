package project.apis.computeapi;

import project.annotations.ConceptualAPIPrototype;

/**
 * Optimized DigitalRootPersistenceAPI.
 * Uses arithmetic operations 
 */
public class FastDigitalRootPersistenceAPI implements DigitalRootPersistenceAPI {

    @Override
    @ConceptualAPIPrototype
    public String processDigitalRootPersistence(int number) {
        // Handle edge case
        if (number == 0) return "0";
        
        // Use Math.abs to handle negative numbers properly
        int n = Math.abs(number);
        
        // Count steps needed to reach a single digit (persistence)
        int persistence = 0;
        
        // Keep summing digits until we reach a single digit
        while (n >= 10) {
            int sum = 0;
            // Extract and sum each digit using modulo and division
            while (n > 0) {
                sum += n % 10;
                n /= 10;
            }
            n = sum;
            persistence++;
        }
        
        // Return the final digital root (not the persistence)
        return String.valueOf(n);
    }
}
