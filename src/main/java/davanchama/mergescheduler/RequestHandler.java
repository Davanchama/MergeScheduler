package davanchama.mergescheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Handles all requests and reads all inputs.
 *
 * @author Davanchama
 */
public class RequestHandler {
    /**
     * the scanner that will read the users input
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * requests and reads the names of the participants.
     * if the user types quit return null.
     *
     * @return the names of the participants
     */
    public ArrayList<String> requestNames() {
        boolean hasValidInput = false;
        HashSet<String> names = new HashSet<String>();
        while (!hasValidInput) {
            System.out.println("Enter the names of the persons separated by commas without spaces");
            String input = scanner.nextLine();

            if (input.equals("quit")) {
                return null;
            }

            //hashset discards doubled inputs
            names = new HashSet<String>(Arrays.asList(input.split(",")));

            if (names.size() > 2) {
                hasValidInput = true;
            } else {
                System.out.println("More than 2 different persons needed");
            }
        }
        return new ArrayList<>(names);
    }

    /**
     * requests and reads the current week.
     * if the user types quit return null.
     *
     * @return the current week entered.
     */
    public Integer requestWeek() {
        boolean hasValidInput = false;
        Integer week = 0;
        while (!hasValidInput) {
            System.out.println("Enter the current week number");
            String input = scanner.nextLine();

            if (input.equals("quit")) {
                return null;
            }
            try {
                week = Integer.valueOf(input);
                hasValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Not a number");
            }
        }
        return week;
    }

    /**
     * requests and reads a boolean.
     * if the user types quit return null.
     *
     * @return the boolean entered
     */
    public Boolean requestYesNo() {
        while (true) {
            System.out.println("Enter y/n");
            String input = scanner.nextLine();

            if (input.equals("quit")) {
                return null;
            } else if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            }
        }
    }

}
