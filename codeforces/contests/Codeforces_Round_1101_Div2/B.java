package codeforces.contests.Codeforces_Round_1101_Div2;

// author: someear1h

import java.util.*;
import java.io.*;

public class B {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);
        var st = new StringTokenizer(br.readLine());
        
        int t = Integer.parseInt(st.nextToken());
        while(t-->0) {

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            long[] a = new long[n];
            for(int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }

            long mini = 1000000009;
            long currSum = 0;
            for(int i = 0; i < n; i++) {
                currSum += a[i];
                long currAvg = currSum / (i+1);

                mini = Math.min(mini, currAvg);

                out.print(mini + " ");
            }

            out.println();
        }   

        out.close();
    }
}
