package enhanced_switches;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String day;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter which day it is: ");
        day = sc.nextLine();

//        switch(day) {
//            case "Monday" -> System.out.println("This is a weekday");
//            case "Tuesday" -> System.out.println("This is a Weekday");
//            case "Wednesday" -> System.out.println("This is a Weekday");
//            case "Thursday" -> System.out.println("This is a Weekday");
//            case "Friday" -> System.out.println("This is a Weekday");
//            case "Saturday" -> System.out.println("This is a Weekend");
//            case "Sunday" -> System.out.println("This is a Weekend");
//            default -> System.out.printf("%s is not a day", day);
//        }

        switch(day) {
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" ->
                System.out.println("This is a weekday");
            case "Saturday", "Sunday" ->
                System.out.println("This is a weekend");
            default -> System.out.printf("%s is not a day", day);
        }

        sc.close();
    }
}
