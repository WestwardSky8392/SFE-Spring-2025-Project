package project.apis.computeapi;

import project.annotations.ConceptualAPIPrototype;
import java.math.BigInteger;

/**
 * Optimized DigitalRootPersistenceAPI.
 * Uses arithmetic operations for better performance.
 */
public class FastDigitalRootPersistenceAPI implements DigitalRootPersistenceAPI {

    @Override
    @ConceptualAPIPrototype
    public String processDigitalRootPersistence(int number) {
        return processDigitalRootPersistence(BigInteger.valueOf(number));
    }

    /**
     * Optimized digital root calculation for arbitrarily large numbers using BigInteger.
     */
    public String processDigitalRootPersistence(BigInteger number) {
        if (number == null) return "error: null input";
        BigInteger n = number.abs();
        if (n.equals(BigInteger.ZERO)) return "0";
        // Keep summing digits until we reach a single digit
        while (n.compareTo(BigInteger.TEN) >= 0) {
            BigInteger sum = BigInteger.ZERO;
            while (n.compareTo(BigInteger.ZERO) > 0) {
                sum = sum.add(n.mod(BigInteger.TEN));
                n = n.divide(BigInteger.TEN);
            }
            n = sum;
        }
        return n.toString();
    }
}
