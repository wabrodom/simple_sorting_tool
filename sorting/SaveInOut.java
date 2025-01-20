package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveInOut {
    private static final List<String> inputOutput = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * custom save output, add to List
     */

    public static void println(boolean printToFile) {
        if (!printToFile) {
            System.out.println();
        }

        inputOutput.add("\n");
    }

    public static void print(boolean printToFile, String message) {
        if (!printToFile) {
            System.out.print(message);
        }

        inputOutput.add(message + "\n");
    }

    public static void println(boolean printToFile, String message) {
        if (!printToFile) {
            System.out.println(message);
        }

        // inputOutput.add(message + "\n");
        inputOutput.add(message + "\n");
    }

    public static void printf(boolean printToFile, String template, Object... args) {
        String formattedString = String.format(template, args);

        if (!printToFile) {
            System.out.printf(template, args);
        }

        // inputOutput.add(formattedString + "\n");
        inputOutput.add(formattedString);
    }


    public static List<String> getInputOutput() {
        return new ArrayList<>(inputOutput);
    }

    public static void clearInputOutput() {
        inputOutput.clear();
    }

    /**
     *custom scanner for saved input text. to List
     * not using in this project
     */

    // public static String scanner() {
    //     String inputValue = scanner.nextLine();
    //     inputOutput.add(inputValue + "\n");
    //     return inputValue;
    // }

}
