import java.util.Arrays;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        int totSum = 0;
        for(int num : nums) totSum += num;

        if(totSum - target < 0) return 0;
        if((totSum - target) % 2 == 1) return 0;

        int s2 = (totSum - target) / 2;

        int[][] dp = new int[nums.length][s2 + 1];
        for(int[] r : dp) Arrays.fill(r, -1);

        return f(nums.length - 1, nums, s2, dp);
    }

    private int f(int idx, int[] nums, int target, int[][] dp) {
        // base case
        if(idx == 0) {
            if(nums[0] == 0 && target == 0) return 2;
            if(target == 0 || nums[0] == target) return 1;

            return 0;
        }

        if(dp[idx][target] != -1) return dp[idx][target];

        int notTaken = f(idx - 1, nums, target, dp);
        int taken = 0;
        if(nums[idx] <= target) {
            taken = f(idx - 1, nums, target - nums[idx], dp);
        }

        return dp[idx][target] = taken + notTaken;
    }
}
