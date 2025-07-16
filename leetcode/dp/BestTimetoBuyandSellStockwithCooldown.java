

public class BestTimetoBuyandSellStockwithCooldown {
    private static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2];

        for (int idx = n - 1; idx >= 0; idx--) {
                for (int hold = 0; hold <= 1; hold++) {
                    int doNothing = dp[idx + 1][hold];

                    int doSomething;
                    if (hold == 1) {
                        // sell
                        doSomething = prices[idx] + dp[idx + 2][0];
                    } else {
                        // buy
                        doSomething = -prices[idx] + dp[idx + 1][1];
                    }

                    dp[idx][hold] = Math.max(doNothing, doSomething);
                }
        }

        return dp[0][0];
    }    
    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};

        System.out.println(maxProfit(prices));
    }
}

