package miniprojects;

import java.util.Random;

public class SnailRace {
    public static void main() {
        System.out.println("The great race begins!");

        // implementing an interface (not preferred way)
        Runnable flashLogic = new Runnable() {
            @Override   
            public void run() {
                race("Flash (Runnable)");
            }
        };

        Thread flash = new Thread(flashLogic);

        // anonymous class extending a class (thread)
        Thread turbo = new Thread() {
            @Override
            public void run() {
                race("Turbo (extending a class)");
            }
        };

        Thread shadow = new Thread(new Runnable() {
            @Override   
            public void run() {
                race("Shadow (inline)");
            }
        });

        flash.start();
        turbo.start();
        shadow.start();

        System.out.println("They are off! Main ends");

    }

    private static void race(String snailName) {
        Random random = new Random();
        int finishLine = 5;

        for(int meter = 1; meter <= finishLine; meter++) {
            System.out.println(snailName + " has reached meter " + meter);

            try {
                int sleepTime = random.nextInt(500) + 100;
                Thread.sleep(sleepTime);
            }
            catch(InterruptedException e) {
                System.out.println(snailName + " tripped");
            }
        }

        System.out.println("Snail " + snailName + " Finished the race first");
    }
}
