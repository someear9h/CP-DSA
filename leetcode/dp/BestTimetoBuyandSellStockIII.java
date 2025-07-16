
public class BestTimetoBuyandSellStockIII {
    private static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][3][2];

        // Base cases are implicitly 0 because Java initializes int[][][] with 0

        for (int idx = n - 1; idx >= 0; idx--) {
            for (int tran = 1; tran <= 2; tran++) {
                for (int hold = 0; hold <= 1; hold++) {
                    int doNothing = dp[idx + 1][tran][hold];

                    int doSomething;
                    if (hold == 1) {
                        // sell → only if tran > 0
                        doSomething = prices[idx] + dp[idx + 1][tran - 1][0];
                    } else {
                        // buy
                        doSomething = -prices[idx] + dp[idx + 1][tran][1];
                    }

                    dp[idx][tran][hold] = Math.max(doNothing, doSomething);
                }
            }
        }

        return dp[0][2][0]; // start with full 2 transactions and no stock
    }

    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};

        System.out.println(maxProfit(prices));
    }
}
