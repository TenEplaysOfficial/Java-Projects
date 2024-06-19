import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int randomNumber = (int) (Math.random() * 100) + 1;
        int maxAttempts = 5;
        int attemptCount = 0;
        System.out.println("I'm thinking of a number between 1 and 100. Can you guess it?");

        while (attemptCount < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attemptCount++;

            if (guess == randomNumber) {
                System.out.println("Congratulations! You guessed the number in " + attemptCount + " attempts.");
                break;
            } else if (guess > randomNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Too low! Try again.");
            }
        }

        if (attemptCount == maxAttempts) {
            System.out.println("Sorry, you ran out of guesses. The number was " + randomNumber);
        }
    }
}
