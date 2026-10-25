package machine_coding_LLD.DesignRateLimiter.factory;

import machine_coding_LLD.DesignRateLimiter.enums.RateLimiterType;
import machine_coding_LLD.DesignRateLimiter.model.RateLimiterConfig;
import machine_coding_LLD.DesignRateLimiter.ratelimiter.FixedWindowRateLimiter;
import machine_coding_LLD.DesignRateLimiter.ratelimiter.RateLimiter;
import machine_coding_LLD.DesignRateLimiter.ratelimiter.SlidingWindowCounterRateLimiter;
import machine_coding_LLD.DesignRateLimiter.ratelimiter.SlidingWindowLogRateLimiter;
import machine_coding_LLD.DesignRateLimiter.ratelimiter.TokenBucketLimiter;

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
