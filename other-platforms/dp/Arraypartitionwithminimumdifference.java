public class  Arraypartitionwithminimumdifference {
    public static int minSubsetSumDifference(int []nums, int n) {
        int totSum = 0;
        for(int num : nums) totSum += num;

        int k = totSum;
        boolean[][] dp = new boolean[n][k + 1];

        for(int i = 0; i < n; i++) dp[i][0] = true;
        if(nums[0] <= totSum) dp[0][nums[0]] = true;

        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= k; j++) {
                boolean nt = dp[i - 1][j];
                boolean t = false;

                if(nums[i] <= j) t = dp[i - 1][j - nums[i]];

                dp[i][j] = t || nt;
            }
        }

        int mini = (int)1e9;
        
        for(int i = 0; i <= k; i++) {
            if(dp[n - 1][i]) {
                int s1 = i;
                int s2 = totSum - i;
                mini = Math.min(mini, Math.abs(s1 - s2));
            }
        }

        return mini;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 2, 8};
        System.out.println(minSubsetSumDifference(nums, nums.length));
    }
}
