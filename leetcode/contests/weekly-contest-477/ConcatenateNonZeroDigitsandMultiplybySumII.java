import java.io.*;
import java.util.*;

public class ConcatenateNonZeroDigitsandMultiplybySumII {
    private static final int MOD = (int)1e9 + 7;
    public static int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int q = queries.length;

        int[] nonZeroCnt = new int[n+1];
        long[] prefixVal = new long[n+1];
        long[] prefixDigitSum = new long[n+1];
        long[] pow10 = new long[n+1];
        pow10[0] = 1;

        for(int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i-1] * 10) % MOD;
        }

        for(int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            nonZeroCnt[i+1] = nonZeroCnt[i];
            prefixVal[i+1] = prefixVal[i];
            prefixDigitSum[i+1] = prefixDigitSum[i];
            
            if(digit != 0) {
                nonZeroCnt[i+1]++;
                prefixVal[i+1] = (prefixVal[i] * 10 + digit) % MOD;
                prefixDigitSum[i+1] += digit;
            }
        }

        int[] ans = new int[q];
        for(int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int nonZeros = nonZeroCnt[r+1] - nonZeroCnt[l];
            long digitSum = prefixDigitSum[r+1] - prefixDigitSum[l];

            long x = 0;
            if(nonZeros == 0) {
                x = 0;
            } else {
                long leftContri = (prefixVal[l] * pow10[nonZeros]) % MOD;
                x = (prefixVal[r+1] - leftContri + MOD) % MOD;
            }

            long result = (x * digitSum) % MOD;
            ans[i] = (int) result;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int n = Integer.parseInt(br.readLine());

            var st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            
            int[][] q = new int[n][2];
            for(int i = 0; i < n; i++) {
                q[i][0] = Integer.parseInt(st.nextToken());
                q[i][1] = Integer.parseInt(st.nextToken());
            }

            int[] res = sumAndMultiply(s, q);
            for(int r : res) {
                out.print(r + " ");
            }
            
            out.println();
        }
    }
}
