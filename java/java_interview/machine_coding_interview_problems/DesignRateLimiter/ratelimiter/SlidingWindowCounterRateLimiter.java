package java_interview.machine_coding_interview_problems.DesignRateLimiter.ratelimiter;

import java_interview.machine_coding_interview_problems.DesignRateLimiter.enums.RateLimiterType;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.model.RateLimiterConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SlidingWindowCounterRateLimiter extends RateLimiter {

    private static class WindowState {
        long currentWindowId;
        int currentCount;
        int previousCount;
    }

    private final Map<String, WindowState> state = new ConcurrentHashMap<>();

    public SlidingWindowCounterRateLimiter(RateLimiterConfig config) {
        super(config, RateLimiterType.SLIDING_WINDOW_COUNTER);
    }

    @Override
    public boolean allowRequest(String userId) {
        long nowSeconds = System.currentTimeMillis() / 1000;
        long windowId = nowSeconds / config.getWindowInSecs();
        long elapsedInWindow = nowSeconds % config.getWindowInSecs();
        // how much of the previous window's traffic still "counts" against us,
        // e.g. 0.7 means we're 30% into the current window
        double previousWindowWeight = 1.0 - ((double) elapsedInWindow / config.getWindowInSecs());

        AtomicBoolean allowed = new AtomicBoolean(false);

        state.compute(userId, (id, ws) -> {
            if (ws == null) {
                ws = new WindowState();
                ws.currentWindowId = windowId;
            } else if (ws.currentWindowId != windowId) {
                // roll forward: today's "current" becomes tomorrow's "previous",
                // but only if it's the very next window -- otherwise there's a
                // gap (user was idle) and previous traffic no longer counts
                ws.previousCount = (windowId - ws.currentWindowId == 1) ? ws.currentCount : 0;
                ws.currentCount = 0;
                ws.currentWindowId = windowId;
            }

            double estimatedRequests = ws.previousCount * previousWindowWeight + ws.currentCount;
            if (estimatedRequests < config.getMaxRequests()) {
                ws.currentCount++;
                allowed.set(true);
            }
            return ws;
        });

        return allowed.get();
    }
}
