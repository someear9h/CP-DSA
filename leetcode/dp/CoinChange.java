import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for(int[] r : dp) {
            Arrays.fill(r, -1);
        }

        
        int ans = f(n - 1, amount, coins, dp);

        return ans >= (int)1e9 ? - 1 : ans;
    }

    // function gives the minimum coins used to make 'target' till i'th index  
    public int f(int idx, int target, int[] nums, int[][] dp) {
        // base case
        if(idx == 0) {
            if(target % nums[idx] == 0) {
                return target / nums[idx];
            }
            
            return (int)1e9;
        }

        // already computed
        if(dp[idx][target] != -1) return dp[idx][target];

        // pick and skip logic
        int skip = f(idx - 1, target, nums, dp);

        int pick = (int)1e9;
        if(nums[idx] <= target) {
            pick = 1 + f(idx, target - nums[idx], nums, dp);
        }

        return dp[idx][target] = Math.min(pick, skip);
    }

    // tabulation -> 
    
    // public int coinChange(int[] coins, int amount) {
    //     int n = coins.length;
    //     int[][] dp = new int[n][amount + 1];

    //     // base cases
    //     for(int a = 0; a <= amount; a++) {
    //         if(a % coins[0] == 0) {
    //             dp[0][a] = a / coins[0];
    //         } else dp[0][a] = (int)1e9;
    //     }

    //     for(int i = 1; i < n; i++) {
    //         for(int j = 0; j <= amount; j++) {
    //             int skip = dp[i - 1][j];

    //             int pick = (int)1e9;
    //             if(coins[i] <= j) {
    //                 pick = 1 + dp[i][j - coins[i]];
    //             }

    //             dp[i][j] = Math.min(pick, skip);
    //         }
    //     }

    //     int ans = dp[n - 1][amount];
    //     return ans >= (int)1e9 ? -1 : ans;
    // }

    public static void main(String[] args) {
        CoinChange sol = new CoinChange();

        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(sol.coinChange(coins, amount));
    }
}
