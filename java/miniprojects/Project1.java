package miniprojects;

import java.util.Scanner;

public class Project1 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // JAVA BANKING PROGRAM

        // DECLARE VARIABLES

        double balance = 0;
        boolean isRunning = true;
        int choice;

        while(isRunning) {

            // DISPLAY MENU
            System.out.println();
            System.out.println("****************");
            System.out.println("BANKING PROGRAM");
            System.out.println("****************");
            System.out.println("1. Show balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.println("****************");
            System.out.println();

            // GET AND PROCESS USER'S CHOICE
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice) {
                case 1 -> showBalance(balance);

                case 2 -> {
                    double depositAmount = deposit();
                    balance += depositAmount;
                    System.out.printf("\nAmount $%.2f Deposited Successfully. Current Balance: $%.2f\n", depositAmount, balance);
                }

                case 3 -> {
                    double withdrawBalance = withdraw(balance);
                    if(withdrawBalance != -1) {
                        balance -= withdrawBalance;
                        System.out.printf("\nWithdrawn $%.2f Successfully. Available Balance: $%.2f\n", withdrawBalance, balance);
                    }
                }

                case 4 -> isRunning = false;
                default -> System.out.println("Invalid Choice");
            }
        }

        // EXIT MESSAGE
        System.out.println("*****************************");
        System.out.println("You have exited the program");
        System.out.println("*****************************");

        sc.close();
    }

    // METHOD TO SHOW BALANCE
    static void showBalance(double balance) {
        System.out.println("-----------------------");
        System.out.printf("$%.2f\n", balance);
        System.out.println("-----------------------");
    }

    // METHOD TO DEPOSIT MONEY
    static double deposit() {
        double amount;
        System.out.print("Enter amount to be deposited: ");
        amount = sc.nextDouble();

        if(amount < 0) {
            System.out.println("Amount cant be negative");
            return 0;
        }

        else if(amount == 0) {
            System.out.println("Amount cant be zero");
            return 0;
        }

        else {
            return amount;
        }
    }

    // METHOD TO WITHDRAW MONEY
    static double withdraw(double balance) {
        double amount;
        System.out.print("Enter amount to be withdrawn: ");
        amount = sc.nextDouble();

        if(amount > balance) {
            System.out.println("INSUFFICIENT BALANCE");
            return -1;
        } else if(amount < 0) {
            System.out.println("Amount cant be negative.");
            return -1;
        } else {
            return amount;
        }
    }
}