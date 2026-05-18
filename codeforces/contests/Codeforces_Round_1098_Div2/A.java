package codeforces.contests.Codeforces_Round_1098_Div2;

// author: someear1h

import java.util.*;
import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);
        var st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        while(t--> 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int zeroCnt = 0, cnt1 = 0, cnt2 = 0;
            int[] w = new int[n];
            for(int i = 0; i < n; i++) {
                w[i] = Integer.parseInt(st.nextToken());
                if(w[i] == 0) zeroCnt++;
                if(w[i] == 1) cnt1++;
                if(w[i] == 2) cnt2++;
            }

            int pairs = Math.min(cnt1, cnt2);
            cnt1 -= pairs;
            cnt2 -= pairs;

            int ans1 = cnt1 / 3;
            int cnt22 = 0;
            while(cnt2 >= 3) {
                cnt22++;
                cnt2 -= 3;
            }

            out.println(zeroCnt + pairs + ans1 + cnt22);
        }

        out.flush();
    }
}

/**
 * {2, 2, 2} or {1, 1, 1}
 * 
 */