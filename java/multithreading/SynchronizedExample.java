package multithreading;

public class SynchronizedExample {
    /**
     * Problem: 
     * context switching between threads can cause race condtions 
     * If Thread A and Thread B both try to withdraw 100 at the exact same time, 
     * they both pass the if statement, and your balance becomes -100.
     * 
     * Solution:
     * Every single object in Java (even a simple Object obj = new Object();) has a hidden, 
     * built-in lock called a Monitor. When you use the synchronized keyword, 
     * you are telling a thread to grab that physical Monitor lock.
     * 
     * in simple words, thread B waits till thread A finished and vice versa
     * 
     * When you add synchronized to a method, it implicitly uses the this object as the lock.
     * Thread A calls withdraw(). It grabs the Monitor lock for this specific BankAccount instance.
     * Thread B calls withdraw(). The JVM sees the lock is taken. Thread B is put into a 
     * BLOCKED state at the OS level. It goes to sleep.
     * Thread A finishes the math safely and exits the method. 
     * The JVM releases the lock and wakes up Thread B.
     * Thread B acquires the lock. It checks if (balance >= amount), 
     * sees the balance is now 0, and safely skips the withdrawal.
     */

    private int balance = 100;

    public synchronized void withdraw(int amount, String person) {
        System.out.println(person + " is checking the balance...");
        if(balance >= amount) {
            System.out.println(person + " sees enough money! Preparing to withdraw...");
            
            // --- THE DANGER ZONE ---
            // We use Thread.sleep to force the CPU to pause this thread.
            // This simulates an OS Context Switch perfectly.
            try {
                Thread.sleep(100);
            } catch(InterruptedException ex) {}
                
            balance = balance - amount;
            System.out.println(person + " completes withdrawal. New balance is: " + balance);
        } else {
            System.out.println(person + " sees insufficient funds. Withdrawal cancelled.");
        }
    }

    public static void main(String[] args) {
        SynchronizedExample account = new SynchronizedExample();

        Thread husband = new Thread(() -> {
            account.withdraw(100, "husband");
        });

        Thread wife = new Thread(() -> {
            account.withdraw(100, "wife");
        });

        husband.start();
        wife.start();

        /**
         * without synchrnised
         * new Balance is -100 after all thread work
         * 
         * and with synchronised one person sees insufficient balance and 
         * one makes the transaction
         * 
         * note: we can not use synchronised on variables
         */
    }
}
