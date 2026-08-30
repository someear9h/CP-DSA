package java_interview.machine_coding_interview_problems.DesignRateLimiter.factory;

import java_interview.machine_coding_interview_problems.DesignRateLimiter.enums.RateLimiterType;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.model.RateLimiterConfig;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.ratelimiter.FixedWindowRateLimiter;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.ratelimiter.RateLimiter;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.ratelimiter.SlidingWindowCounterRateLimiter;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.ratelimiter.SlidingWindowLogRateLimiter;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.ratelimiter.TokenBucketLimiter;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimiterType algo, RateLimiterConfig config) {
        return switch(algo) {
            case FIXED_WINDOW -> new FixedWindowRateLimiter(config);
            case TOKEN_BUCKET -> new TokenBucketLimiter(config);
            case SLIDING_WINDOW_LOG -> new SlidingWindowLogRateLimiter(config);
            case SLIDING_WINDOW_COUNTER -> new SlidingWindowCounterRateLimiter(config);
            default -> throw new IllegalArgumentException("Unknow algorithm " + algo);
        };
    }
}
