import java.io.*;
import java.util.*;

public class Solution {
    static int n;
    static int k;
    static long[] cost, tobuy;
    static long[] a;
    static long[] b;

    static boolean canMake(long x) {
        // to bake x cookies
        cost = new long[n];
        tobuy = new long[n];
        for(int i= 0; i < n; i++) {
            cost[i] = x * a[i];
        }

        for(int i = 0; i < n; i++) {
            tobuy[i] = Math.max(0, cost[i] - b[i]);
        }

        long tpn = 0;
        for(int i = 0; i < n; i++) {
            tpn += tobuy[i];
        }

        return tpn <= k;
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        a= new long[n]; b = new long[n];
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        // binary search thing here
        long l = 0,h = 2_000_000_000_000L;
        long ans = 0;
        while(l <= h) {
            long mid = l + (h - l) / 2;
            if(canMake(mid)) {
                ans = mid;
                l = mid+1;
            } else {
                h = mid -1;
            }
        }

        System.out.println(ans);
    }
}