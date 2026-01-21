/**
* someear1h
*/

import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                var st = new StringTokenizer(br.readLine());
                long n = Long.parseLong(st.nextToken());
                long k = Long.parseLong(st.nextToken());

                out.println(solve(n, k));
            }
        }
    }

    static long solve(long n, long k) {
        long l = n;
        long r = n;
        long depth = 0;

        while(k <= r) {
            if(l <= k && r >= k) {
                return depth;
            }
            
            l = l / 2;
            // ceil(a/b) -> a+b-1 / b here b =2 we need to divide by two
            r = (r + 2-1) / 2;

            depth++;
        }

        return -1;
    }
}