import java.util.*;
import java.io.*;

public class BurstBalloons {
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        
        List<Integer> numsL = new ArrayList<>();
        numsL.add(1);
        for(int num : nums) numsL.add(num);
        numsL.add(1);

        int[][] dp = new int[n + 2][n + 2];
        
        for(int i = n; i >= 1; i--) {
            for(int j = 1; j <= n; j++) {
                if(i > j) continue;

                int mx = (int)-1e9;

                for(int k = i; k <= j; k++) {
                    int coins = numsL.get(i - 1) * numsL.get(k) * numsL.get(j + 1) +
                    dp[i][k - 1] + dp[k + 1][j];

                    mx = Math.max(mx, coins);
                }

                dp[i][j] = mx;
            }
        }

        return dp[1][n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] nums = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            out.println(maxCoins(nums));
            
            out.flush();
        }
    }
}
