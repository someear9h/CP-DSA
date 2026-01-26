import java.io.*;
import java.util.*;

public class D {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                int n = Integer.parseInt(br.readLine());
                
                var st = new StringTokenizer(br.readLine());
                long[] a = new long[n];
                for(int i = 0; i < n; i++) {
                    a[i] = Long.parseLong(st.nextToken());
                }

                st = new StringTokenizer(br.readLine());
                long[] b = new long[n];
                for(int i = 0; i < n; i++) {
                    b[i] = Long.parseLong(st.nextToken());
                }
                

                out.println(solve(a, b, n));
            }
        }

    }


    static long solve(long[] a, long[] b, int n) {
        long currSum = 0;
        int h = 0;

        Arrays.sort(a);
        long mx = Long.MIN_VALUE;
        for(int i =1; i <= n; i++) {
            long diff = a[n-i];
            // i -> number of swords

            while(h < n && currSum + b[h] <= i) {
                currSum += b[h];
                h++;
            }

            mx = Math.max(mx, diff * h);
        }

        return mx;
    }
}
