// package codeforces.contests.Codeforces_Round_1103_Div3;

// author: someear1h

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);
        var st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        
        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            
            boolean f = true;

            int[] bucket = new int[n];
            for(int i = 0; i < n; i++) {
                if(s.charAt(i) == '1') {
                    bucket[i%k]++;
                }
            }

            for(int i = 0; i < n; i++) {
                if(bucket[i] % 2 != 0) {
                    f = false;
                }
            }
            
            out.println(f ? "YES" : "NO");
        }

        out.close();
    }
}