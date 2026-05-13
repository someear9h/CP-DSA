package threading;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
         * Threading -> Allows a program to run multiple tasks simultaneously
         * Helps improve performance with time-consuming operations
         * (file i/o network communications, or any background tasks)
         *
         * How to create a thread
         * option 1 -> Extend the thread class (simpler, but limited)
         * option 2 -> implement the runnable interface (better)
         */

        Scanner sc = new Scanner(System.in);

        System.out.println("You have 10 seconds to enter ur name.");

//        for(int i = 1; i <= 5; i++) {
//            try {
//                Thread.sleep(1000);
//            }
//            catch(InterruptedException ex) {
//                System.out.println("Thread was interrupted");
//            }
//
//            if(i == 5) {
//                System.out.println("Time's up");
//            }
//        }

        // we cant reach the below code because of the for loop we commented
        // is running on the same thread
        // the timer completes then we get prompted "enter ur name"
        // that's when multithreading comes into play

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.setDaemon(true); // finishes code when main thread is done
        thread.start();

        System.out.print("enter ur name: ");

        String name = sc.nextLine();
        System.out.println("your name is " + name);

        sc.close();
    }
}
