package printf_package;

import java.util.Scanner;

public class Exercise {
    public static void main(String[] args) {
        // compound interest calculator

        Scanner sc = new Scanner(System.in);

        double principal;
        double rate;
        int timesCompounded;
        int years;
        double amount;

        System.out.print("Enter principal amount: ");
        principal = sc.nextDouble();

        System.out.print("Enter the interest Rate in (%): ");
        rate = sc.nextDouble() / 100;

        System.out.print("Enter # of times Compounded: ");
        timesCompounded = sc.nextInt();

        System.out.print("Enter years: ");
        years = sc.nextInt();

        System.out.print("\n");

        amount = principal * Math.pow(1 + (rate/timesCompounded), timesCompounded * years);

        System.out.printf("The amount after %d years is %.2f\n", years, amount);

        sc.close();
    }
}
