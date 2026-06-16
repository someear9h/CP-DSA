// author: someear1h

import java.io.*;
import java.util.*;

public class A {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);
        var st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        
        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            int maxElement = -10000;
            int minElement = 10000;
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                maxElement = Math.max(maxElement, arr[i]);
                minElement = Math.min(minElement, arr[i]);
            }
            // the difference between greates and smallest element + 1?
            System.out.println(maxElement - minElement + 1);
        }

        out.close();
    }
}