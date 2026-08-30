package java_interview.machine_coding_interview_problems.DesignRateLimiter.service;

import java_interview.machine_coding_interview_problems.DesignRateLimiter.enums.RateLimiterType;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.enums.UserTier;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.factory.RateLimiterFactory;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.model.RateLimiterConfig;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.model.User;
import java_interview.machine_coding_interview_problems.DesignRateLimiter.ratelimiter.RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {
    Map<UserTier, RateLimiter> rateLimiters = new HashMap<>();

    public RateLimiterService() {
        rateLimiters.put(UserTier.FREE_USER,
                RateLimiterFactory.createRateLimiter(
                        RateLimiterType.TOKEN_BUCKET,
                        new RateLimiterConfig(10, 60) // 10 req / 60 sec
                ));

        rateLimiters.put(UserTier.PREMIUM_USER,
                RateLimiterFactory.createRateLimiter(
                        RateLimiterType.FIXED_WINDOW,
                        new RateLimiterConfig(100, 60) // 100 req / 60 sec
                ));
    }

    public boolean allowRequest(User user) {
        RateLimiter limiter = rateLimiters.get(user.getTier());
        if (limiter == null) {
            throw new IllegalArgumentException("No limiter configured for tier: " + user.getTier());
        }
        return limiter.allowRequest(user.getUserId());
    }
}
