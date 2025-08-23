import java.util.Arrays;

public class MinimumSumAfterDivisibleSumDeletions {
    private static long minArraySum(int[] nums, int k) {
        long[] dp = new long[k];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        long res = 0;
        for(int num : nums) {
            res += num;
            int remainder = (int)(res % k);
            res = dp[remainder] = Math.min(dp[remainder], res);
        }

        return res;
    }
    public static void main(String[] args) {
        int[] nums = {3,1,4,1,5};
        int k = 3;

        System.out.println(minArraySum(nums, k));
    }
}
