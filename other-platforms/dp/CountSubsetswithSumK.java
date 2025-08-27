public class CountSubsetswithSumK {
    static int MOD = (int)(1e9 + 7);

    static int findWays(int[] num, int k) {
        int n = num.length;
        int[][] dp = new int[n][k + 1];

        // Base case
        if (num[0] == 0) {
            dp[0][0] = 2; // we can pick or not pick zero ? 2 ways
        } else {
            dp[0][0] = 1; // we can always make sum=0 by not picking
            if (num[0] <= k) {
                dp[0][num[0]] = 1;
            }
        }

        // Bottom-up DP
        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= k; target++) {
                int notTaken = dp[ind - 1][target];
                int taken = 0;
                if (num[ind] <= target) {
                    taken = dp[ind - 1][target - num[ind]];
                }
                dp[ind][target] = (notTaken + taken) % MOD;
            }
        }

        return dp[n - 1][k];
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 4, 5};
        int k = 5;
        
        System.out.println(findWays(arr, k));
    }
}
