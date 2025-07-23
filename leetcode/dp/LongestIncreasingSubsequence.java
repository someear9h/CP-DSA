import java.util.*;

public class LongestIncreasingSubsequence {
    private static int lis(int[] nums) {
        int[] dp = new int[nums.length + 1];

        Arrays.fill(dp, 1);

        for(int i = 0; i < nums.length; i++) {
            for(int pi = 0; pi < i; pi++) {
                if(nums[i] > nums[pi] && dp[i] < dp[pi] + 1) {
                    dp[i] = dp[pi] + 1;
                }
            }
        }

        // max element in the dp array is the answer
        int mx = 0;
        for(int i : dp) {
            mx = Math.max(mx, i);
        }

        return mx;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};

        System.out.println(lis(nums));
    }
}
