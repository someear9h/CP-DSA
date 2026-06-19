package multithreading;

public class VolatileExample {
    /**
     * Problem:
     * Imagine thread A and thread B, in computers they have their own cache
     * to avoid going to read/write to the main memory (RAM).
     * now they take a variable into their cache and start working on them
     * 
     * now main thread is B and it works on the main memory (RAM)
     * but thread A has copied the variable and put in its cache and doesnt know 
     * updates done to the variable by thread B. 
     * 
     * Thread A suffers from cache staleness.
     * 
     * Solution:
     * volatile keyword: Declare the variable volatile
     * it makes the threads work/write on the main memory and make sure all threads
     * see up to date data
     * 
     * It does NOT solve the Race Condition / Check-Then-Act problem we discussed earlier. 
     * If two threads do count++ on a volatile int, you will still lose data.
     */

    // private static boolean running = true;
    private static volatile boolean running = true;
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            while(running) {
                // some heavy task thread A does
            }

            System.out.println("Thread A is done working");
        });

        threadA.start();

        Thread.sleep(1000); // 1 sec

        // now if there is no use of volatile keyword main thread would turn to false
        // and the threadA would have no idea and think the running = true and goes into
        // infinite loop.

        // using volatile gives correct output
        // first we tell thread A to stop and then A stops when it sees main thread
        // modified the running flag to false
        System.out.println("Telling thread A to stop");
        running = false;
    }
}
