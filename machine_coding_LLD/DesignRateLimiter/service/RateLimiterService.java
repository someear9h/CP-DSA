package machine_coding_LLD.DesignRateLimiter.service;

import java.util.HashMap;
import java.util.Map;

import machine_coding_LLD.DesignRateLimiter.enums.RateLimiterType;
import machine_coding_LLD.DesignRateLimiter.enums.UserTier;
import machine_coding_LLD.DesignRateLimiter.factory.RateLimiterFactory;
import machine_coding_LLD.DesignRateLimiter.model.RateLimiterConfig;
import machine_coding_LLD.DesignRateLimiter.model.User;
import machine_coding_LLD.DesignRateLimiter.ratelimiter.RateLimiter;

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
