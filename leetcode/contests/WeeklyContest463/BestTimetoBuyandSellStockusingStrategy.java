public class BestTimetoBuyandSellStockusingStrategy {
    public static long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long[] prev1 = new long[n + 1];
        long[] prev2 = new long[n + 1];

        prev1[0] = prev2[0] = 0;

        for(int i = 0; i < n; i++) {
            prev1[i + 1] = prev1[i] + prices[i];
            prev2[i + 1] = prev2[i] + (long) prices[i] * strategy[i];
        }

        int l = 0, r = k;

        long ans = query(prev2, 0, n);

        while(r <= n) {
            long curr = query(prev2, 0, l)
                        + query(prev1, l + k/ 2, r)
                        + query(prev2, r, n);
            
            ans = Math.max(ans, curr);
            l++;
            r++;
        }

        return ans;
    }

    static long query(long[] prev, int l, int r) {
        return prev[r] - prev[l];
    }    

    public static void main(String[] args) {
        int k = 2;
        int[] prices = {4,2,8};
        int[] strategy = {-1,0,1};

        System.out.println(maxProfit(prices, strategy, k));
    }
}
