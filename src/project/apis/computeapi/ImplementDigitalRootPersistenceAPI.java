package project.apis.computeapi;

import project.annotations.ConceptualAPIPrototype;

/**
 * Implementation of the DigitalRootPersistenceAPI.
 * This class processes numbers to determine their digital root.
 * This implementation uses string operations, which is less efficient
 * for large numbers compared to arithmetic operations. (Self Note)
 */
public class ImplementDigitalRootPersistenceAPI implements DigitalRootPersistenceAPI {

    @Override
    @ConceptualAPIPrototype
    public String processDigitalRootPersistence(int number) {
        // Calculate digital root persistence (number of steps to reach a single digit)
        int n = Math.abs(number);
        int steps = 0;
        while (n >= 10) {
            int sum = 0;
            int temp = n;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            n = sum;
            steps++;
        }
        return String.valueOf(steps);
    }
    
    /**
     * Exception handling to prevent uncaught exceptions from reaching
     * computation boundary.
     */
    public void testingForErrorsProcess() {
        try {
            processDigitalRootPersistence(0);
            System.out.println("Successfully Processed!");
        } catch (Exception e) {
            System.out.println("Failure Processing!");
        }
    }
}
