package exam04;

import java.util.*;

/**
 * Raten
 */
public class Raten {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter amount of tries:");
            final int tries = sc.nextInt();

            final int secretNumber = ((int) (Math.random() * 1000)) + 1;

            boolean hasWon = false;

            for (int i = 0; i < tries && !hasWon; i++) {
                System.out.println("Enter your next guess:");
                int guess = sc.nextInt();
                if (guess == secretNumber) {
                    hasWon = true;
                    System.out.println("You correctly guessed the secret number " + secretNumber);
                } else {
                    System.out.println("The number you guessed is too " + ((guess < secretNumber) ? "small." : "big."));
                }
            }
        }

        System.out.println("You ran out of guesses. Better luck next time!");
    }

}
