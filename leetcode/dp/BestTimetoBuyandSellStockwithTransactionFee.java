public class BestTimetoBuyandSellStockwithTransactionFee {
    private static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        for(int i = n - 1; i >= 0; i--) {
            for(int hold = 0; hold <= 1; hold++) {
                int doNothing = dp[i + 1][hold];

                int doSomething;

                if(hold == 1) {
                    // sell
                    // when we sell we have to subtract the fees
                    doSomething = (prices[i] + dp[i + 1][0]) - fee;
                } else {
                    doSomething = -prices[i] + dp[i + 1][1];
                }

                dp[i][hold] = Math.max(doSomething, doNothing);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] prices = {1,3,2,8,4,9};
        int fee = 2;

        System.out.println(maxProfit(prices, fee));
    }
}
