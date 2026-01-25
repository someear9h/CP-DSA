import java.io.*;
import java.util.*;

public class D {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                var st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                out.println(solve((long)n, (long)k));
            }
        }
    }
    static int[][] C = new int[31][31];

    static long solve(long n, long k) {
        for(int i = 0; i < 30; i++) {
            C[i][0] = 1;

            for(int j = 1; j <= i; j++) {
                C[i][j] = C[i-1][j-1] + C[i-1][j];
            }
        }

        long tempN = n;
        long d = 0;
        while(tempN > 1) {
            tempN /= 2;
            d++;
        }
        
        long ans = 0;
        
        for(int maxBit = 0; maxBit < d; maxBit++) {
            for(int cntBit = 1; cntBit <= maxBit+1; cntBit++) {
                if(cntBit + maxBit > k) {
                    ans += C[maxBit][cntBit-1];
                }
            }
        }

        if(d+1 > k) {
            ans++;
        }

        return ans;
    }
}