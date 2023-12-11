import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Horner {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter degree of polynomial:");
            final int degree = sc.nextInt();
            sc.nextLine();  // fix for sc.nextLine()

            System.out.println("Enter coefficients, seperated by ' ' (spaces) in ascending order (a0, a1, ...):");
            String[] coeffStr = sc.nextLine().trim().replaceAll("\s+", " ").split(" ");
            if (coeffStr.length != degree + 1)
                throw new IllegalArgumentException(
                        "The amount of coefficients is not compatible with the specified degree!");
            double[] coefficients = Arrays.stream(coeffStr)
                    .map((str) -> Double.parseDouble(str))
                    .mapToDouble(Number::doubleValue)
                    .toArray();

            while (true) {
                System.out.println("Enter function argument x (or ^C to quit):");
                double x;
                try {
                    x = sc.nextDouble();

                    double lastCoefficient = coefficients[degree];
                    double y = lastCoefficient;
                    double z = lastCoefficient;
                    for (int k = 1; k <= degree; k++) {
                        y = x*y + + coefficients[degree-k];
                        if (k < degree) z = x*z + y;
                    }
                    System.out.println(String.format(
                        "f(x)  = %g\n" +
                        "f'(x) = %g",
                        y, z));
                } catch (NumberFormatException ex) {
                    System.exit(0);
                }
            }
        }
    }

}
