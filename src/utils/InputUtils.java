package utils;

import java.util.Scanner;

public class InputUtils {

    private static final Scanner sc = new Scanner(System.in);

    public static int readInt() {
        int number = 0;
        boolean validInput = false;

        while (!validInput) {
            if (sc.hasNextLine()) {
                String input = sc.nextLine().trim();
                try {
                    number = Integer.parseInt(input);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a integer.");
                }
            }
        }
        return number;
    }

    public static String readString() {
        while (true) {
            if (sc.hasNextLine()) {
                return sc.nextLine().trim();
            } else {
                System.out.println("No input found. Please enter a string:");
            }
        }
    }

    public static void closeScanner() {
        sc.close();
    }
}
