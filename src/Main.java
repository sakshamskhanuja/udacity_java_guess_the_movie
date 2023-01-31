import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Loads the game.
        Game game = new Game(new File("Movies.txt"));

        // Scans the user input.
        Scanner scanner = new Scanner (System.in);

        // Stores whether user has won the game.
        boolean userWon = false;

        for (int i = 10; i > 0; i--) {
            System.out.println("You are guessing: " + game.getProgress());

            System.out.println("You have guessed (" + game.getWrongGuessCount() + ") wrong letters: " +
                    game.getWrongGuessList());

            System.out.print("Guess a letter: ");

            // Scanner scans the user input.
            String input = scanner.nextLine();

            if (input.length() > 1) {
                System.out.println("Enter a single character.");
                i++;
            } else {
                // Checks the user guess.
                game.checkGuess(input);

                // Checks if user has won the game.
                if (game.getProgress().equals(game.getMovie())) {
                    // Updating status.
                    userWon = true;
                    System.out.println("\nYou win!\nYou have guessed '" + game.getMovie() + "' correctly.");
                    break;
                }
            }
        }

        // Checks whether user has lost the game.
        if (!userWon) {
            System.out.println("\nBetter luck next time. The movie was \"" + game.getMovie() + "\".");
        }

        // Closes the scanner.
        scanner.close();
    }
}