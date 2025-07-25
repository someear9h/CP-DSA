import java.util.Arrays;

public class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int mx = 1;

        Arrays.fill(dp, 1);

        for(int i = 1; i < n; i++) {
            if(nums[i] <= nums[i - 1]) {
                dp[i] = 1;
            } else {
                dp[i] += dp[i - 1];
            }
        }

        for(int i : dp) {
            mx = Math.max(mx, i);
        }

        return mx;
    }
}
