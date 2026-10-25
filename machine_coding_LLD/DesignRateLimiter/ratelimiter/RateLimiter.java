package machine_coding_LLD.DesignRateLimiter.ratelimiter;

import machine_coding_LLD.DesignRateLimiter.enums.RateLimiterType;
import machine_coding_LLD.DesignRateLimiter.model.RateLimiterConfig;

public abstract class RateLimiter {
    protected final RateLimiterConfig config;
    protected final RateLimiterType type;
    public abstract boolean allowRequest(String userId);

    public RateLimiter(RateLimiterConfig config, RateLimiterType type) {
        this.config = config;
        this.type = type;
    }
}
