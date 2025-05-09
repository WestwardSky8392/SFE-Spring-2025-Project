package project.apis.computeapi;

import project.annotations.ConceptualAPIPrototype;

/**
<<<<<<< HEAD
 * Optimized DigitalRootPersistenceAPI.
 * Uses arithmetic operations 
=======
 * Optimized: DigitalRootPersistenceAPI.
 * Uses arithmetic instead of string/array conversion for better performance
>>>>>>> 9e46c967bd2fb4a2e856e54059fc0e762b407d5c
 */
public class FastDigitalRootPersistenceAPI implements DigitalRootPersistenceAPI {

    @Override
    @ConceptualAPIPrototype
    public String processDigitalRootPersistence(int number) {
<<<<<<< HEAD
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
=======
        // Fast algorithm: use arithmetic
        if (number == 0) return "0";
        int steps = 0;
        int n = Math.abs(number);
        while (n >= 10) {
            int sum = 0;
>>>>>>> 9e46c967bd2fb4a2e856e54059fc0e762b407d5c
            while (n > 0) {
                sum += n % 10;
                n /= 10;
            }
            n = sum;
<<<<<<< HEAD
            persistence++;
        }
        
        // Return the final digital root (not the persistence)
=======
            steps++;
        }
        // Return the digital root as a string (to match interface)
>>>>>>> 9e46c967bd2fb4a2e856e54059fc0e762b407d5c
        return String.valueOf(n);
    }
}
