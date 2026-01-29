/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class LineTrip {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                var st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());


                st = new StringTokenizer(br.readLine());
                int[] a = new int[n];

                for(int i = 0; i < n; i++) {
                    a[i] = Integer.parseInt(st.nextToken());
                }

                out.println(solve(a, n, x));
            }
        }
    }

    private static int solve(int[] a, int n, int x) {
        int maxDist = a[0];

        for(int i = 0; i < n-1; i++) {
            maxDist = Math.max(maxDist, a[i+1] - a[i]);
        }

        int ldi = 2 * (x- a[n-1]);
        maxDist = Math.max(maxDist, ldi);

        return maxDist;
    }
}