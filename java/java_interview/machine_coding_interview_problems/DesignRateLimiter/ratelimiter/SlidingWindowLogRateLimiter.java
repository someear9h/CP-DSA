package java_interview.machine_coding_interview_problems.DesignRateLimiter.ratelimiter;

import java_interview.machine_coding_interview_problems.DesignRateLimiter.enums.RateLimiterType;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.model.RateLimiterConfig;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SlidingWindowLogRateLimiter extends RateLimiter {
    public SlidingWindowLogRateLimiter(RateLimiterConfig config) {
        super(config, RateLimiterType.SLIDING_WINDOW_LOG);
    }

    Map<String, Deque<Long>> requestLog = new ConcurrentHashMap<>();

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        long now = System.currentTimeMillis() / 1000;

        requestLog.compute(userId, (id, reqLog) -> {
            if(reqLog == null) reqLog = new ArrayDeque<>();

            if(!reqLog.isEmpty() && (now - reqLog.peek() >= config.getWindowInSecs())) {
                reqLog.poll();
            }

            if(reqLog.size() < config.getMaxRequests()) {
                reqLog.offer(now);
                allowed.set(true);
            }

            return reqLog;
        });

        return allowed.get();
    }
}
