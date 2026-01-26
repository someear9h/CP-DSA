import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)){
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                var st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());
    
                st = new StringTokenizer(br.readLine());
                long[] a = new long[n];
                for(int i = 0; i < n; i++) a[i] = Long.parseLong(st.nextToken());
    
                st = new StringTokenizer(br.readLine());
                long[] b = new long[n];
                for(int i = 0; i < n; i++) b[i] = Long.parseLong(st.nextToken());
    
                solve(a, b, n, q, br, out);
            }
            
        }
    }  

    static void solve(long[] a, long[] b, int n, int q, BufferedReader br, PrintWriter out) throws IOException {
        long[] opt = new long[n];
        opt[n-1] = Math.max(a[n-1], b[n-1]);

        for(int i = n-2; i >= 0; i--) {
            long bestChoice = Math.max(a[i], b[i]);
            opt[i] = Math.max(bestChoice, opt[i+1]);
        }

        long[] pre = new long[n+1];
        for(int i = 0; i < n; i++) {
            pre[i+1] = pre[i] + opt[i];
        }

        while(q-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            l--; 
            r--;

            long ans = pre[r+1] - pre[l];
            out.print(ans + " ");
        }
        out.println();
    }
}