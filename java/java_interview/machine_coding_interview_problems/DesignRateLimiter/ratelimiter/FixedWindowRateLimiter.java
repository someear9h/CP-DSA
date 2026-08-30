package java_interview.machine_coding_interview_problems.DesignRateLimiter.ratelimiter;

import java_interview.machine_coding_interview_problems.DesignRateLimiter.enums.RateLimiterType;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.model.RateLimiterConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedWindowRateLimiter extends RateLimiter {
    // keep the window state as windowId, count of tokens used
    private static class WindowState {
        long windowId;
        int count;

        WindowState(long windowId, int count) {
            this.windowId = windowId;
            this.count = count;
        }
    }

    public FixedWindowRateLimiter(RateLimiterConfig config) {
        super(config, RateLimiterType.FIXED_WINDOW);
    }

    // for each user we have the window state, we use concurrent hash map
    // because we lock the state for each user for concurrency
    // key as userId, value as WindowState
    Map<String, WindowState> state = new ConcurrentHashMap<>();

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        // get current time in ms -> convert to seconds -> divide by window size
        // this gives us a unique window id for a window size, and it is used to check
        // if we are in current window or not

        // Concretely, with 60-second windows:
        // t=0 to t=59 seconds → windowId = 0
        // t=60 to t=119 seconds → windowId = 1
        // t=120 to t=179 seconds → windowId = 2
        long currentWindow = System.currentTimeMillis() / 1000 / config.getWindowInSecs();

        state.compute(userId, (id, ws) -> {
           if(ws == null || ws.windowId != currentWindow) {
               ws = new WindowState(currentWindow, 1);
               allowed.set(true);
           }
           else if(ws.count < config.getMaxRequests()) {
               ws.count++;
               allowed.set(true);
           }

           return ws;
        });

        return allowed.get();
    }
}
