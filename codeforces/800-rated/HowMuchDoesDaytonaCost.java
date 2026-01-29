/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class HowMuchDoesDaytonaCost {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                var st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                st = new StringTokenizer(br.readLine());
                int[] a = new int[n];
                boolean f = false;
                for(int i = 0; i < n; i++) {
                    a[i] = Integer.parseInt(st.nextToken());
                    if(a[i] == k) {
                        out.println("YES");
                        f = true;
                        break;
                    }
                }
                
                if(!f) {
                    out.println("NO");
                }

            }
        }
    }
}