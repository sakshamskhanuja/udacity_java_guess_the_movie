import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    // Stores the file which contains movies.
    private final File file;

    // Stores a random movie from file.
    private final String randomMovie;

    // Stores the user made progress.
    private String progress;

    // Stores a random movie from File having each character is in separate index.
    private final String[] randomMovieChar;

    // Stores the user made progress where each character is in separate index.
    private final String[] progressChar;

    // Stores the user made guesses which were not present in the randomMovieChar.
    private final ArrayList<String> wrongGuessList;

    // Stores the number of wrong guesses user has made.
    private int wrongGuess;

    public Game(File file) {
        this.file = file;
        randomMovie = getRandomMovie();
        randomMovieChar = randomMovie.split("");
        progress = initializeProgress();
        progressChar = progress.split("");
        wrongGuessList = new ArrayList<>();
        wrongGuess = 0;
    }

    /**
     * Initializes the progress by replacing every character (except whitespace) in the randomly chosen movie with "_".
     * <p>
     * Example: pulp fiction -> ____ _______
     *
     * @return A underscored String.
     */
    private String initializeProgress() {
        String[] characters = Arrays.copyOf(randomMovieChar, randomMovieChar.length);

        // Replaces every character except whitespace with "_".
        for (int i = 0; i < characters.length; i++) {
            if (!characters[i].equals(" ")) {
                characters[i] = "_";
            }
        }

        // Converts characters to a String.
        return getString(characters);
    }

    /**
     * Creates a String out of the elements of a String array.
     *
     * @param array Contains only String elements.
     * @return String representation of array.
     */
    private static String getString(String[] array) {
        StringBuilder builder = new StringBuilder();
        for (String element : array) {
            builder.append(element);
        }
        return builder.toString();
    }

    /**
     * Checks whether the entered guess is present in {@link Game#randomMovie}.
     *
     * @param guess Single character in a String.
     */
    public void checkGuess(String guess) {
        // Stores whether the guess was present in randomMovieChar.
        boolean guessWorked = false;
        for (int i = 0; i < progressChar.length; i++) {
            // Checks if guess letter is present in randomMovieChar.
            if (guess.equals(randomMovieChar[i])) {
                // Update status.
                guessWorked = true;
                progressChar[i] = guess;
            }
        }

        // Updates the wrongGuesses list if the guess was not found in randomMovieChar.
        if (!guessWorked) {
            wrongGuessList.add(guess);

            // Updating wrongGuess.
            wrongGuess++;
        } else {
            // Updating progress.
            progress = getString(progressChar);
        }
    }

    /**
     * Returns how many wrong guess user has made.
     */
    public int getWrongGuessCount() {
        return wrongGuess;
    }

    /**
     * Returns the String representation of wrongGuessList.
     */
    public String getWrongGuessList() {
        StringBuilder builder = new StringBuilder();
        for (String element : wrongGuessList) {
            builder.append(element).append(" ");
        }
        return builder.toString();
    }

    /**
     * Returns the user made progress.
     */
    public String getProgress() {
        return progress;
    }

    /**
     * Returns a random movie.
     */
    public String getMovie() {
        return randomMovie;
    }

    /**
     * Loads a list of movies from a File.
     *
     * @return ArrayList of type {@link String}.
     */
    private ArrayList<String> loadMovies() {
        // Stores a list of movies.
        ArrayList<String> movieList = new ArrayList<>();
        if (file.exists()) {
            try {
                // Scans the file for movies.
                Scanner scanner = new Scanner(file);

                // Adds movies to movie list.
                while (scanner.hasNextLine()) {
                    movieList.add(scanner.nextLine());
                }

                // Closes the scanner.
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        }
        return movieList;
    }

    /**
     * Finds a random movie in a {@link File}.
     *
     * @return A movie name.
     */
    private String getRandomMovie() {
        // Stores the list of movies.
        ArrayList<String> movieList = loadMovies();

        // Returns a random movie.
        return movieList.get(new Random().nextInt(movieList.size()));
    }
}
