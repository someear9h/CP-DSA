/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class EverybodyLikesGoodArrays {
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
                
                int ops = 0;
                for(int i = 0; i < n-1; i++) {
                    if(a[i] % 2 == a[i+1] % 2) {
                        ops++;
                    }
                }

                out.println(ops);
            }
        }
    }
}