package java_interview.machine_coding_interview_problems.DesignRateLimiter.ratelimiter;

import java_interview.machine_coding_interview_problems.DesignRateLimiter.enums.RateLimiterType;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.model.RateLimiterConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class TokenBucketLimiter extends RateLimiter {
    private static class BucketState {
        int tokens;
        long lastRefillInMillis;

        BucketState(int tokens, long lastRefillInMillis) {
            this.tokens = tokens;
            this.lastRefillInMillis = lastRefillInMillis;
        }
    }

    // for constructor, we only need to know the config
    // the type is not passed to us, this class tells us which type
    // using explicit enum
    public TokenBucketLimiter(RateLimiterConfig config) {
        super(config, RateLimiterType.TOKEN_BUCKET);
    }

    Map<String, BucketState> buckets = new ConcurrentHashMap<>();

    @Override
    public boolean allowRequest(String userId) {
        AtomicBoolean allow = new AtomicBoolean(false);
        long now = System.currentTimeMillis();
        buckets.compute(userId, (id, state) -> {
            // if fresh bucket is created it has max tokens and last refilled now
            if(state == null) state = new BucketState(config.getMaxRequests(), now);

            // the window has 10 requests per 60 seconds.
            // how many seconds for 1 token? 60 / 10 = 6 secs
            double refillIntervalSecs = (double) config.getWindowInSecs() / config.getMaxRequests();
            // how much time passed since we last refill bucket
            long timeElapsedSecs = (now - state.lastRefillInMillis) / 1000;
            // how many tokens to add?
            // time elapsed / refill interval
            int tokensToAdd = (int) (timeElapsedSecs / refillIntervalSecs);

            if(tokensToAdd > 0) {
                state.tokens = Math.min(config.getMaxRequests(), state.tokens + tokensToAdd);
                state.lastRefillInMillis = now;
            }

            if(state.tokens > 0) {
                state.tokens--;
                allow.set(true);

            }
            return state;
        });

        return allow.get();
    }
}
