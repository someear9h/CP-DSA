import java.util.Arrays;

public class PathsinMatrixWhoseSumIsDivisiblebyK {
    static int MOD = (int)1e9 + 7;
    static int[][][] dp;

    public static int numberOfPaths(int[][] grid, int k) {        
        int m = grid.length, n = grid[0].length;
        dp = new int[m][n][k];

        for(int[][] mat : dp) {
            for(int[] r : mat) {
                Arrays.fill(r, -1);
            }
        }

        return f(0, 0, 0, grid, k);
    }

    static int f(int r, int c, int sum, int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;

        if(r < 0 || r == m || c < 0 || c == n) return 0;

        sum += grid[r][c];

        if(r == m - 1 && c == n - 1) {
            return (sum % k == 0) ? 1 : 0;
        }

        if(dp[r][c][sum % k] != -1) {
            return dp[r][c][sum % k];
        }


        int right = f(r, c + 1, sum, grid, k);
        int down = f(r + 1, c, sum, grid, k);

        return dp[r][c][sum % k] = (right + down) % MOD;
    }
}
