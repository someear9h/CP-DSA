package ExceptionHandling.eg;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        * Exceptions: An event that interrupts normal flow of the program
        * (e.g. Diving by zero, file not found, mismatch input type)
        * try{} catch{} finally{}
        * */

//        try {
//            System.out.println(10 / 0);
//        } catch(ArithmeticException ex) {
//            System.out.println("Cant divide by zero");
//        }

        // by putting scanner in try block we don't have
        // to worry about closing the scanner
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter a number: ");
            int number = sc.nextInt();
            System.out.println(number);
        } catch (InputMismatchException ex) {
            System.out.println("That wasn't a number");
        } catch (Exception ex) {
            // in case we don't anticipate what went wrong (acts as a safety net)
            System.out.println("Something went wrong");
        } finally {
            System.out.println("Finally block always executes");
        }
    }
}
