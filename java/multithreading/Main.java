package multithreading;

public class Main {
    public static void main(String[] args) {
        /*
        * Multithreading -> Enables to run multiple threads concurrently
        * (Thread = A set of instructions that run independently)
        * useful for background tasks or time-consuming operations
        * */

        System.out.println("GAME START!");

        Thread thread1 = new Thread(new MyRunnable("Ping"));
        Thread thread2 = new Thread(new MyRunnable("Pong"));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }
        catch(InterruptedException e) {
            System.out.println("Main thread was interrupted");
        }


        System.out.println("GAME ENDS!");
    }
}