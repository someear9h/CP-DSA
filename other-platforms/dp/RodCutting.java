import java.io.*;
import java.util.*;

public class RodCutting {
    public static int cutRod(int[] price, int N) {
        int n = price.length;
        int[][] dp = new int[n][N + 1];

        // Initialize dp array with -1
        for(int[] row : dp) Arrays.fill(row, -1);

        return f(n - 1, N, price, dp);
    }

    // Recursive function with memoization
    private static int f(int i, int N, int[] price, int[][] dp) {
        // Base case: only length 1 is available
        if (i == 0) return N * price[0];

        if(dp[i][N] != -1) return dp[i][N];

        // Skip this piece
        int skip = f(i - 1, N, price, dp);

        // Pick this piece if it fits
        int pick = Integer.MIN_VALUE;
        int rodLength = i + 1; // piece length (1-based)
        if (rodLength <= N) {
            pick = price[i] + f(i, N - rodLength, price, dp);
        }

        // Store and return the maximum
        dp[i][N] = Math.max(pick, skip);
        return dp[i][N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            
            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                
                StringTokenizer st = new StringTokenizer(br.readLine());
                int[] price = new int[n];
                
                for(int i = 0; i < n; i++) {
                    price[i] = Integer.parseInt(st.nextToken());
                }
                
                out.println(cutRod(price, n));
            }
        }
    }
}