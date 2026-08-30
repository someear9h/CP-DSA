package java_interview.machine_coding_interview_problems.DesignRateLimiter;

import java_interview.machine_coding_interview_problems.DesignRateLimiter.enums.UserTier;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.model.User;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.service.RateLimiterService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static void checkConcurrency(RateLimiterService service) throws InterruptedException {
        User user1 = new User("user1", UserTier.FREE_USER);

        int threads = 20;
        try(ExecutorService executor = Executors.newFixedThreadPool(threads)) {

            CyclicBarrier barrier = new CyclicBarrier(threads);
            CountDownLatch latch = new CountDownLatch(threads);

            for (int i = 1; i <= threads; i++) {
                final int reqNum = i;

                executor.submit(() -> {
                    try {
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    boolean allowed = service.allowRequest(user1);
                    System.out.println(Thread.currentThread().getName() +
                            " | request " + reqNum + " for user1: " + (allowed ? "ALLOWED" : "BLOCKED"));

                    latch.countDown();
                });
            }
            latch.await();
            executor.shutdown();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        checkConcurrency(new RateLimiterService());
//        testTokenBucket();
//        testFixedWindow();
//        testSlidingWindowLog();


    }

//    // ---------- TOKEN BUCKET ----------
//    private static void testTokenBucket() throws InterruptedException {
//        System.out.println("\n===== TOKEN BUCKET (5 req / 10 sec) =====");
//        RateLimiter limiter = RateLimiterFactory.createRateLimiter(
//                RateLimiterType.TOKEN_BUCKET, new RateLimiterConfig(5, 10));
//        String userId = "user1";
//
//        System.out.println("-- Burst of 7 (expect first 5 ALLOWED, last 2 BLOCKED) --");
//        for (int i = 1; i <= 7; i++) {
//            System.out.println("Req " + i + ": " + result(limiter.allowRequest(userId)));
//        }
//
//        System.out.println("-- Wait 4 sec (interval = 10/5 = 2 sec/token, expect ~2 refilled) --");
//        Thread.sleep(4000);
//        for (int i = 1; i <= 3; i++) {
//            System.out.println("Req " + i + ": " + result(limiter.allowRequest(userId)));
//        }
//    }
//
//    // ---------- FIXED WINDOW ----------
//    private static void testFixedWindow() throws InterruptedException {
//        System.out.println("\n===== FIXED WINDOW (3 req / 5 sec) =====");
//        RateLimiter limiter = RateLimiterFactory.createRateLimiter(
//                RateLimiterType.FIXED_WINDOW, new RateLimiterConfig(3, 5));
//        String userId = "user1";
//
//        System.out.println("-- 4 requests instantly (expect first 3 ALLOWED, 4th BLOCKED, same window) --");
//        for (int i = 1; i <= 4; i++) {
//            System.out.println("Req " + i + ": " + result(limiter.allowRequest(userId)));
//        }
//
//        System.out.println("-- Wait 6 sec (new window starts, expect reset) --");
//        Thread.sleep(6000);
//        for (int i = 1; i <= 3; i++) {
//            System.out.println("Req " + i + ": " + result(limiter.allowRequest(userId)));
//        }
//    }
//
//    // ---------- SLIDING WINDOW LOG ----------
//    private static void testSlidingWindowLog() throws InterruptedException {
//        System.out.println("\n===== SLIDING WINDOW LOG (3 req / 10 sec) =====");
//        RateLimiter limiter = RateLimiterFactory.createRateLimiter(
//                RateLimiterType.SLIDING_WINDOW_LOG, new RateLimiterConfig(3, 10));
//        String userId = "user1";
//
//        System.out.println("-- 3 requests spaced 2 sec apart (all ALLOWED, log fills up) --");
//        for (int i = 1; i <= 3; i++) {
//            System.out.println("Req " + i + ": " + result(limiter.allowRequest(userId)));
//            Thread.sleep(2000);
//        }
//
//        System.out.println("-- 4th request immediately (expect BLOCKED, still within 10s of oldest) --");
//        System.out.println("Req 4: " + result(limiter.allowRequest(userId)));
//
//        System.out.println("-- Wait 6 more sec (oldest entries should expire, expect ALLOWED again) --");
//        Thread.sleep(6000);
//        System.out.println("Req 5: " + result(limiter.allowRequest(userId)));
//    }
//
//    private static String result(boolean allowed) {
//        return allowed ? "ALLOWED" : "BLOCKED";
//    }
}
