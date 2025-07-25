import java.util.Arrays;

public class NumberofLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n], ct = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(ct, 1);

        for(int i = 0; i < n; i++) {
            for(int pi = 0; pi < i; pi++) {
                if(nums[i] > nums[pi] && dp[i] < dp[pi] + 1) {
                    dp[i] = dp[pi] + 1;
                    // now length increases(changes) so ct will be unchanged
                    ct[i] = ct[pi];
                } else if(nums[i] > nums[pi] && dp[i] == dp[pi] + 1) {
                    // now length remains same, thus count increases
                    ct[i] += ct[pi];
                }
            }
        }
        
        int mx = 0, res = 0;
        for(int i : dp) mx = Math.max(mx, i);

        for(int i = 0; i < n; i++) {
            if(dp[i] == mx) res += ct[i];
        }

        return res;
    }
}
