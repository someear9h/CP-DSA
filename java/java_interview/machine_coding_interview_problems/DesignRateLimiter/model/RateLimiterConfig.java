package java_interview.machine_coding_interview_problems.DesignRateLimiter.model;

public class RateLimiterConfig {
    private final int maxTokens;
    private final int windowInSecs;

    public RateLimiterConfig(int maxTokens, int windowInSecs) {
        this.maxTokens = maxTokens;
        this.windowInSecs = windowInSecs;
    }

    public int getMaxRequests() {
        return maxTokens;
    }

    public int getWindowInSecs() {
        return windowInSecs;
    }
}
