import java.io.*;
import java.util.StringTokenizer;

public class BestTimetoBuyandSellStockIIII {
    private static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][k + 1][2];

        for(int idx = n - 1; idx >= 0; idx--) {
            for(int tran = 1; tran <= k; tran++) {
                for(int hold = 0; hold <= 1; hold++) {
                    int doNothing = dp[idx + 1][tran][hold];

                    int doSomething;

                    if(hold == 1) {
                        // sell
                        doSomething = prices[idx] + dp[idx + 1][tran - 1][0];
                    } else {
                        // buy
                        doSomething = -prices[idx] + dp[idx + 1][tran][1];
                    }

                    dp[idx][tran][hold] = Math.max(doSomething, doNothing);
                }
            }
        }
        return dp[0][k][0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());

        int[] prices = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        out.println(maxProfit(k, prices));
        out.flush();
    }
}
