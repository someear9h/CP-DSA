public class PlatesBetweenCandles {
    private static int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();

        int[] nearestRight = new int[n];
        int[] nearestLeft = new int[n];
        int[] candleCount = new int[n];
        int[] ans = new int[queries.length];

        // nearest left candle
        int candle = -1;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '|') {
                candle = i;
            }
            nearestLeft[i] = candle;
        }

        // nearest right candle
        candle = -1;
        for(int i = n - 1; i >= 0; i--) {
            if(s.charAt(i) == '|') {
                candle = i;
            }
            nearestRight[i] = candle;
        }

        // candle count on each position
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '|') {
                cnt++;
            }
            candleCount[i] = cnt;
        }

        int idx = 0;
        for(int[] q : queries) {
            int l = q[0], r = q[1];

            int leftCandle = nearestRight[l];
            int rightCandle = nearestLeft[r];

            int d = 0;
            if(leftCandle == -1 || rightCandle == -1) {
                ans[idx] = 0;
            } else {
                d = rightCandle - leftCandle;
                if(d > 1) {
                    ans[idx] = rightCandle - leftCandle + 1 
                    - (candleCount[rightCandle] - candleCount[leftCandle] + 1);
                } else {
                    ans[idx] = 0;
                }
            }
            idx++;
        }

        return ans;
    }    

    public static void main(String[] args) {
        String s = "**|**|***|";
        int[][] queries = {{2, 5}, {5, 9}};

        int[] res = platesBetweenCandles(s, queries);

        for(int r : res) {
            System.out.print(r + " ");
        }
    }
}
