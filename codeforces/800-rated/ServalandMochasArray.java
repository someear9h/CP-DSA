/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class ServalandMochasArray {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());

                var st = new StringTokenizer(br.readLine());

                long[] a = new long[n];
                for(int i = 0; i < n; i++) {
                    a[i] = Long.parseLong(st.nextToken());
                }

                boolean f = false;
                for(int i = 0; i < n; i++) {
                    if(f) break;
                    for(int j = i+1; j < n; j++) {
                        if(gcd(a[i], a[j]) <= 2) {
                            out.println("YES");
                            f = true;
                            break;
                        } 
                    }
                }

                if(!f) {
                    out.println("NO");
                }
            }
        }
    }

    private static long gcd(long a, long b) {
        while(b!=0) {
            long temp = b;
            b = a % b;
            a = temp;
        } 

        return a;
    }
}