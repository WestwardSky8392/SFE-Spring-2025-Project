package project.apis.computeapi;

import project.annotations.ConceptualAPIPrototype;

/**
 * Optimized: DigitalRootPersistenceAPI.
 * Uses arithmetic instead of string/array conversion for better performance
 */
public class FastDigitalRootPersistenceAPI implements DigitalRootPersistenceAPI {

    @Override
    @ConceptualAPIPrototype
    public String processDigitalRootPersistence(int number) {
        // Fast algorithm: use arithmetic
        if (number == 0) return "0";
        int steps = 0;
        int n = Math.abs(number);
        while (n >= 10) {
            int sum = 0;
            while (n > 0) {
                sum += n % 10;
                n /= 10;
            }
            n = sum;
            steps++;
        }
        // Return the digital root as a string (to match interface)
        return String.valueOf(n);
    }
}
