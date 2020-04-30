package menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {
    /**
     * A validation that the input is integer
     * @param scanner
     * @param message
     * @param notANumberError message if the input is not an integer
     * @return
     */
    public static int integerVal(Scanner scanner, String message, String notANumberError)
    {
        int intInput = 0;

        do {
            System.out.println(message);

            try {
                intInput = scanner.nextInt();

            }catch(InputMismatchException e) {
                System.out.println(notANumberError);
                scanner.next();
            }
        }while(intInput == 0);

        return intInput;
    }
}
