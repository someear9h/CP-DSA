package enhanced_switches;

import java.util.Scanner;

public class Exercise {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double num1;
        double num2;
        char operator;
        double result = -1;

        System.out.print("Enter the first number: ");
        num1 = sc.nextDouble();

        System.out.print("Enter operator (+, -, /, *, ^): ");
        operator = sc.next().charAt(0);

        System.out.print("Enter the second number: ");
        num2 = sc.nextDouble();

        switch(operator) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '/' -> {
                if(num2 == 0) {
                    System.out.println("\nCant divide by zero");
                } else
                    result = num1 / num2;
            }
            case '*' -> result = num1 * num2;
            case '^' -> result = Math.pow(num1, num2);
            default -> {
                result = -1;
                System.out.printf("\n%c is not a valid operator\n", operator);
            }
        }

        if(result !=-1)
            System.out.printf("\nResult: %.2f\n", result);

        sc.close();
    }
}
