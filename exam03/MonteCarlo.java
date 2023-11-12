import java.util.*;

public class MonteCarlo {

    public static int m, n;

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                m = 0;
                try {
                    System.out.println("Enter new value for n (or 'exit' to exit):");
                    n = sc.nextInt();
                } catch (InputMismatchException ex) {
                    break;
                }

                for (int i = 0; i < n; i++) {
                    double x = Math.random();
                    double y = Math.random();

                    boolean inCircle = x * x + y * y <= 1;
                    if (inCircle)
                        m++;
                }

                double approxPi = 4f * m / n;
                double error = Math.abs(Math.PI - approxPi);

                System.out.println("PI approximation: " + approxPi);
                System.out.println("absolute error: " + error);
            }
        }
    }

}
