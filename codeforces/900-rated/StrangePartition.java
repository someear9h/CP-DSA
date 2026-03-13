
/**
someear1h 
*/

import java.io.*;
import java.util.*;

public class StrangePartition {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while (t-- > 0) {
                var st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                long x = Long.parseLong(st.nextToken());

                st = new StringTokenizer(br.readLine());
                long[] a = new long[n];
                long maxB = 0, minB = 0;
                long s = 0;
                for (int i = 0; i < n; i++) {
                    a[i] = Long.parseLong(st.nextToken());
                    maxB += (Math.ceilDiv(a[i], x));
                    s += a[i];
                }

                minB = Math.ceilDiv(s, x);

                out.println(minB + " " + maxB);

            }
        }
    }
}