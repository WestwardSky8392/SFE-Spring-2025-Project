import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DigitalRootCalculatorTest {

    @Test
    public void testCalculatePersistence() {
        DigitalRootCalculator calculator = new DigitalRootCalculator();

        // Single-digit numbers should return 0 iterations
        assertEquals(0, calculator.calculatePersistence(0), "Failed for input: 0");
        assertEquals(0, calculator.calculatePersistence(5), "Failed for input: 5");

        // Multi-digit numbers
        int result14 = calculator.calculatePersistence(14);
        System.out.println("Input: 14, Expected: 1, Actual: " + result14);
        assertEquals(1, result14, "Failed for input: 14"); // 1+4=5 (1 iteration)

        int result39 = calculator.calculatePersistence(39);
        System.out.println("Input: 39, Expected: 2, Actual: " + result39);
        assertEquals(2, result39, "Failed for input: 39"); // 3+9=12 -> 1+2=3 (2 iterations)

        int result999 = calculator.calculatePersistence(999);
        System.out.println("Input: 999, Expected: 3, Actual: " + result999);
        assertEquals(3, result999, "Failed for input: 999"); // 9+9+9=27 -> 2+7=9 (3 iterations)

        // Large numbers
        int result12345 = calculator.calculatePersistence(12345);
        System.out.println("Input: 12345, Expected: 2, Actual: " + result12345);
        assertEquals(2, result12345, "Failed for input: 12345"); // 1+2+3+4+5=15 -> 1+5=6 (2 iterations)
    }
}
