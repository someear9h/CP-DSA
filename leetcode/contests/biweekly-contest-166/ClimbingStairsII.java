import java.util.*;

public class ClimbingStairsII {
    public static int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return f(0, n, costs, dp);
    }

    private static int f(int idx, int n, int[] costs, int[] dp) {
        if(idx > n) return (int)1e9;
        if(idx == n) return 0;

        if(dp[idx] != -1) return dp[idx];

        int oneStep = (costs[idx] + 1) + f(idx + 1, n, costs, dp);
        int twoStep = idx + 2 <= n ? 
            (costs[idx + 1] + 4) + f(idx + 2, n, costs, dp): (int)1e9;
        int threeStep = idx + 3 <= n ? 
            (costs[idx + 2] + 9) + f(idx + 3, n, costs, dp) : (int)1e9;

        return dp[idx] = Math.min(oneStep, Math.min(twoStep, threeStep));
    }

    public static void main(String[] args) {
        int n = 4;
        int[] costs = {1,2,3,4};

        System.out.println(climbStairs(n, costs));
    }
}
