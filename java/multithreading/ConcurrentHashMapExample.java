package multithreading;

import java.util.*;
import java.util.concurrent.*;

public class ConcurrentHashMapExample {
    // failure with hashmap tells why we need concurrent hashmap
    // Imagine we are building a website analytics tool. We want to count how many 
    // times a page is visited. We spin up 100 threads, and each thread registers 1,000 visits. 
    // We should end up with 100,000 visits.

    public static void main(String[] args) throws InterruptedException{
        // Map<String, Integer> mp = new HashMap<>();
        Map<String, Integer> mp = new ConcurrentHashMap<>();
        mp.put("index.html", 0);
        
        ExecutorService exec = Executors.newFixedThreadPool(100);

        // 100 threads visit page 1000 times
        for(int i = 1; i <= 100; i++) {
            exec.submit(() -> {
                for(int j = 0; j < 1000; j++) {
                    // int currVisits = mp.get("index.html");;
                    // mp.put("index.html", currVisits+1);
                    mp.compute("index.html", (k, v) -> v + 1);
                }
            });
        }

        exec.shutdown();
        exec.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Expected visits: 100000");
        System.out.println("Actual visits:   " + mp.get("index.html"));

        // this is called race condition resulting in lost update
    }
}
