/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t--> 0) {
                var st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());
                
                int[] a = new int[n];

                st = new StringTokenizer(br.readLine());
                for(int i = 0; i < n; i++) {
                    a[i] = Integer.parseInt(st.nextToken());
                }
                
                int[] b = new int[n];
                st = new StringTokenizer(br.readLine());
                for(int i = 0; i < n; i++) {
                    b[i] = Integer.parseInt(st.nextToken());
                }

                solve(a, b, n, q, br, out, st);                
            }
        }
    }

    private static void solve(int[] a, int[] b, int n, int q, 
        BufferedReader br, PrintWriter out, StringTokenizer st) throws IOException {

        for(int i = n-1; i >= 0; i--) {
            if(b[i] > a[i]) a[i] = b[i];

            if(i != n-1 && a[i] < a[i+1]) {
                a[i] = a[i+1];
            }
        }

        long[] presum = new long[n];
        presum[0] = a[0];
        for(int i = 1; i < n; i++) {
            presum[i] = presum[i-1] + a[i];
        }

        // queries
        while(q-->0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            l--;
            r--;
            
            int sum = 0;
            if(l == 0) {
                sum += presum[r];
            } else {
                sum += presum[r] - presum[l-1];
            }

            out.print(sum + " ");
        }

        out.println();
    }
}