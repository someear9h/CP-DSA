/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class WeNeedtheZero {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                var st = new StringTokenizer(br.readLine());
                int xor = 0;
                for(int i = 0; i < n; i++) {
                    int val = Integer.parseInt(st.nextToken());
                    xor ^= val;
                }

                if((n & 1) != 0) {
                    // odd
                    out.println(xor);
                } else {
                    if(xor == 0) {
                        out.println(0);
                    } else {
                        out.println("-1");
                    }
                }
            }
        }
    }
}