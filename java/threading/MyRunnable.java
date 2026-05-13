package threading;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for(int i = 1; i <= 10; i++) {

            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex) {
                System.out.println("Thread was interrupted");
            }

            if(i == 10) {
                System.out.println("Time's up");
                System.exit(0); // exits with code 0 when timer finishes
            }
        }
    }
}