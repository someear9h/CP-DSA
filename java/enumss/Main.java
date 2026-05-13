package enumss;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        * Enums -> (Enumerations) a special kind of class that represents fixed constants
        * they improve code readability and are easy to maintain
        * More efficient with switches when comparing strings
        * */

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a day of a week: ");
        String response = sc.next().toUpperCase();

        try{
            Day day = Day.valueOf(response);
            switch(day) {
                case MONDAY,
                     TUESDAY,
                     WEDNESDAY,
                     THURSDAY,
                     FRIDAY -> System.out.println("This is a weekday");

                case SATURDAY, SUNDAY -> System.out.println("this is a weekend");
            }
        }
        catch(IllegalArgumentException ex) {
            System.out.println(response + " is not a day of the week");
            System.out.println("enter a valid day of the week");
        }

        sc.close();
    }
}
