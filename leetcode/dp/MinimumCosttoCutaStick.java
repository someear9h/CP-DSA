import java.util.*;

public class MinimumCosttoCutaStick {
    private static int minCost(int n, int[] cuts) {
        List<Integer> modCuts = new ArrayList<>();
        for(int i : cuts) modCuts.add(i);
        modCuts.add(0);
        modCuts.add(n);
        Collections.sort(modCuts);

        int c = cuts.length;

        int[][] dp = new int[c + 2][c + 2];
        for(int[] r : dp) Arrays.fill(r, 0);

        for(int i = c; i >= 1; i--) {
            for(int j = 1; j <= c; j++) {
                if(i > j) continue;
                int minCost = (int)1e9;

                for(int k = i; k <= j; k++) {
                    int cost = modCuts.get(j + 1) - modCuts.get(i - 1) + 
                    dp[i][k - 1] +
                    dp[k + 1][j];

                    minCost = Math.min(minCost, cost);
                }

                dp[i][j] = minCost;
            }
        }

        return dp[1][c];
    }

    public static void main(String[] args) {
        int n = 7;
        int[] cuts = {1,3,4,5};

        System.out.println(minCost(n, cuts));
    }
}
