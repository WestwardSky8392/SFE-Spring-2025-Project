public class DigitalRootCalculator {

    public int calculatePersistence(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        if (number < 10) {
            return 0; // Single-digit numbers have 0 iterations
        }
        int iterations = 0;
        while (number >= 10) {
            System.out.println("Current number: " + number); // Debug output
            number = sumDigits(number);
            iterations++;
        }
        System.out.println("Final single-digit number: " + number + ", Iterations: " + iterations); // Debug output
        return iterations;
    }

    private int sumDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
