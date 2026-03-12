
/**
someear1h 
*/

import java.io.*;
import java.util.*;

public class MochaandMath {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while (t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                var st = new StringTokenizer(br.readLine());
                long[] a = new long[n];

                for (int i = 0; i < n; i++) {
                    a[i] = Long.parseLong(st.nextToken());
                }
                long totAND = a[0];
                for (int i = 1; i < n; i++) {
                    totAND &= a[i];
                }
                out.println(totAND);
            }
        }

    }
}