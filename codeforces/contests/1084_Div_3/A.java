
/**
someear1h 
*/

import java.io.*;
import java.util.*;

public class A {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while (t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                var st = new StringTokenizer(br.readLine());
                int[] a = new int[n];
                int[] cnt = new int[11];
                int maxEle = -1;

                for (int i = 0; i < n; i++) {
                    a[i] = Integer.parseInt(st.nextToken());
                    cnt[a[i]]++;
                    maxEle = Math.max(maxEle, a[i]);
                }

                out.println(cnt[maxEle]);
            }
        }
    }
}