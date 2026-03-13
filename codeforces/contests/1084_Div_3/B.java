
/**
someear1h 
*/

import java.io.*;
import java.util.*;

public class B {
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

                if (n == 1) {
                    out.println(1);
                    continue;
                }

                // check if array is sorted or not
                // if yes then return n
                // else return 1
                boolean sorted = true;
                for (int i = 0; i < n - 1; i++) {
                    if (a[i] > a[i + 1]) {
                        sorted = false;
                    }
                }
                if (sorted) {
                    out.println(n);
                } else {
                    out.println(1);
                }

            }
        }
    }
}