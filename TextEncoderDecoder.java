import java.util.Scanner;

public class TextEncoderDecoder {
    // Method to encode a message
    public static String encode(String message, int shift) {
        StringBuilder encoded = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encoded.append((char) ((c - base + shift) % 26 + base));
            } else {
                encoded.append(c);
            }
        }
        return encoded.toString();
    }

    // Method to decode a message
    public static String decode(String message, int shift) {
        return encode(message, 26 - (shift % 26));
    }

    // Display menu
    public static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Encode a message");
        System.out.println("2. Decode a message");
        System.out.println("3. Exit");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int shift = 0;

        System.out.println("Welcome to the Basic Text Encoder and Decoder!");

        while (true) {
            displayMenu();
            System.out.print("Enter your choice (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter the message to encode: ");
                String message = scanner.nextLine();
                System.out.print("Enter the shift value: ");
                shift = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Encoded Message: " + encode(message, shift));
            } else if (choice == 2) {
                System.out.print("Enter the message to decode: ");
                String message = scanner.nextLine();
                System.out.print("Enter the shift value: ");
                shift = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Decoded Message: " + decode(message, shift));
            } else if (choice == 3) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }
}
