import java.util.*;
import java.io.*;

public class B3946 {
    static int memo(int i, int k, int lastTaken, int[][] items, int[][][] dp, int[] factCnt) {
        if(k < 0) return Integer.MIN_VALUE;

        if(i >= items.length) return 0;

        if(dp[i][k][lastTaken] != -1) return dp[i][k][lastTaken];

        int free = lastTaken == 0 ? factCnt[i] : 0;
        
        int pick = 1 + free + memo(i, k - items[i][1], 1, items, dp, factCnt);

        int skip = memo(i+1, k, 0, items, dp, factCnt);

        return dp[i][k][lastTaken] = Math.max(pick, skip);
    }

    static int maxProducts(int[][] items, int budget) {
        int n = items.length;
        int[] factCnt = new int[n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i != j && items[j][0] % items[i][0] == 0) {
                    factCnt[i]++;
                }        
            }
        }

        // dp[i][budget][lastTaken]
        int[][][] dp = new int[n][budget+1][2];
        for(int[][] d2 : dp) {
            for(int[] d1 : d2) {
                Arrays.fill(d1, -1);
            }
        }

        return memo(0, budget, 0, items, dp, factCnt);
    }
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);
        var st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[][] items = new int[n][2];

            for(int i = 0; i < n; i++) {
                items[i][0] = Integer.parseInt(st.nextToken());
                items[i][1] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            int budget = Integer.parseInt(st.nextToken());


            System.out.println(maxProducts(items, budget));
        }

        out.close();
    }
}