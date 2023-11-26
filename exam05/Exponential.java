package exam05;

import java.util.Locale;
import java.util.Scanner;

public class Exponential {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter input x for exp(x): ");
            double x = sc.nextDouble();
            System.out.println("Enter upper bound for the sum: ");
            int N = sc.nextInt();

            // calculate using N iterations
            double y = 1;
            double sum = y;
            for (int i = 1; i <= N; i++) {
                y = x / i * y;
                sum += y;
            }
            System.out.println(String.format("Value of exp(x) after %d iterations: %g", N, sum));

            // calculate until consecutive terms differ by less than (or equal to) 10^-13
            y = 1;
            sum = y;
            double preSum;

            int i = 0;
            do {
                i++;
                preSum = sum;
                y = x / i * y;
                sum += y;
            } while (Math.abs(preSum - sum) > 10e-13);
            System.out.println(String.format("Value of exp(x) after %d iterations with optimized accuracy: %g", i, sum));

            // calculate the same way as above, but use e^(-x)=1/e^x
            double correctedX = x;
            if (x < 0) correctedX = -correctedX;
            y = 1;
            sum = y;

            i = 0;
            do {
                i++;
                preSum = sum;
                y = correctedX / i * y;
                sum += y;
            } while (Math.abs(preSum - sum) > 10e-13);
            if (x < 0) sum = 1f / sum;
            System.out.println(String.format("Corrected value for negative inputs of exp(x) after %d iterations: %g", i, sum));

            // calculate using Math.exp
            System.out.println(String.format("Value of Math.exp(x): %g", Math.exp(x)));

        }
    }

}
