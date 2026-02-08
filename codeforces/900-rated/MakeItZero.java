/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class MakeItZero {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                var st = new StringTokenizer(br.readLine());
                int[] a = new int[n];
                for(int i = 0; i < n; i++) {
                    a[i] = Integer.parseInt(st.nextToken());
                }
                

                if((n & 1) == 0) {
                    out.println(2);
                    out.println(1 + " " + n);
                    out.println(1 + " " + n);
                } else {
                    out.println(4);
                    out.println(1 + " " + (n-1));
                    out.println(1 + " " + (n-1));

                    out.println((n-1) + " " + n);
                    out.println((n-1) + " " + n);
                }
            }
        }
    }
}