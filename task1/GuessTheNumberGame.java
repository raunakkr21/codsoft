import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        final int LOWER = 1;
        final int UPPER = 100;
        final int MAX_ATTEMPTS = 7;

        int roundsWon = 0;
        int totalRounds = 0;
        boolean playAgain;

        System.out.println(" Welcome to the Guess the Number Game!");

        do {
            int numberToGuess = random.nextInt(UPPER - LOWER + 1) + LOWER;
            int attempts = 0;
            boolean guessedCorrectly = false;
            totalRounds++;

            System.out.println("\n Round " + totalRounds);
            System.out.println("Guess a number between " + LOWER + " and " + UPPER);

            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess (" + (MAX_ATTEMPTS - attempts) + " attempts left): ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == numberToGuess) {
                    System.out.println(" Correct! You guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println(" Too low!");
                } else {
                    System.out.println(" Too high!");
                }
            }

            if (!guessedCorrectly) {
                System.out.println(" You've used all attempts! The number was: " + numberToGuess);
            }

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");

        } while (playAgain);

        System.out.println("\n Game Over!");
        System.out.println("Total Rounds Played: " + totalRounds);
        System.out.println("Rounds Won: " + roundsWon);
        System.out.println("Thanks for playing!");

        scanner.close();
    }
}
