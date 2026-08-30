package java_interview.machine_coding_interview_problems.DesignRateLimiter.ratelimiter;

import java_interview.machine_coding_interview_problems.DesignRateLimiter.enums.RateLimiterType;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.model.RateLimiterConfig;

public abstract class RateLimiter {
    protected final RateLimiterConfig config;
    protected final RateLimiterType type;
    public abstract boolean allowRequest(String userId);

    public RateLimiter(RateLimiterConfig config, RateLimiterType type) {
        this.config = config;
        this.type = type;
    }
}
