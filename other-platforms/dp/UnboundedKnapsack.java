import java.io.*;
import java.util.*;

public class UnboundedKnapsack {
    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        int[][] dp = new int[n][w + 1];
        
        int[] val = profit;
        int[] wt = weight;

        for (int j = 0; j <= w; j++) {
            dp[0][j] = (j / wt[0]) * val[0];
        }
        
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= w; j++) {
                int skip = dp[i - 1][j];

                int pick = 0;
                if(wt[i] <= j) {
                    pick = val[i] + dp[i][j - wt[i]];
                }

                dp[i][j] = Math.max(pick, skip);
            }
        }

        return dp[n - 1][w];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {

            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int n = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
    
            st = new StringTokenizer(br.readLine());
            int[] val = new int[n];
            int[] wt = new int[n];
    
            for(int i = 0; i < n; i++) {
                val[i] = Integer.parseInt(st.nextToken());
            }
    
            st = new StringTokenizer(br.readLine());
    
            for(int i = 0; i < n; i++) {
                wt[i] = Integer.parseInt(st.nextToken());
            }
    
            out.println(unboundedKnapsack(n, w, val, wt));
        }

    }

}
