import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    private static final String[] WORDS = {
        "java", "hangman", "programming", "computer", "developer"
    };

    private static final String[] HANGMAN_PICS = {
        "+---+\n    |\n    |\n    |\n   ===",
        "+---+\nO   |\n    |\n    |\n   ===",
        "+---+\nO   |\n|   |\n    |\n   ===",
        "+---+\nO   |\n|\\  |\n    |\n   ===",
        "+---+\nO   |\n|\\  |\n/   |\n   ===",
        "+---+\nO   |\n|\\  |\n/ \\ |\n   ==="
    };

    public static void main(String[] args) {

        Random random = new Random();
        String wordToGuess = WORDS[random.nextInt(WORDS.length)];

        int wrongGuesses = 0;
        ArrayList<Character> guessedLetters = new ArrayList<>();
        StringBuilder currentGuess = new StringBuilder("_".repeat(wordToGuess.length()));
        Scanner scanner = new Scanner(System.in);

        // game loop
        while (wrongGuesses < HANGMAN_PICS.length - 1) {
            System.out.println(HANGMAN_PICS[wrongGuesses]);
            System.out.println("Word to guess: " + currentGuess);
            System.out.print("Guess a letter: ");
            char guessedLetter = scanner.next().toLowerCase().charAt(0);

            if (guessedLetters.contains(guessedLetter)) {
                System.out.println("You've already guessed that letter. Try again.");
                continue;
            }

            guessedLetters.add(guessedLetter);

            if (wordToGuess.indexOf(guessedLetter) >= 0) {
                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (wordToGuess.charAt(i) == guessedLetter) {
                        currentGuess.setCharAt(i, guessedLetter);
                    }
                }

                if (currentGuess.toString().equals(wordToGuess)) {
                    System.out.println("Congratulations! You've guessed the word: " + wordToGuess);
                    break;
                }
            } else {
                wrongGuesses++;
            }
        }

        if (wrongGuesses == HANGMAN_PICS.length - 1) {
            System.out.println(HANGMAN_PICS[wrongGuesses]);
            System.out.println("Sorry, you've been hanged! The word was: " + wordToGuess);
        }

        scanner.close();
    }
}
