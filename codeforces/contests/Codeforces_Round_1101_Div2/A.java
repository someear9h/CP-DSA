package codeforces.contests.Codeforces_Round_1101_Div2;

import java.util.*;
import java.io.*;

public class A{
    public static void main(String[] args) throws IOException {
        var br =new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);
        var st =new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        while(t-->0) {
            st =new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            st =new StringTokenizer(br.readLine());
            long[] a = new long[n];

            
            for(int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }
            
            Arrays.sort(a);

            // i and n-1-i
            int cnt = 0;
            for(int i = 0; i < n/2; i++) {
                if(a[i] != a[n-1-i]) {
                    cnt++;
                }
            }

            out.println(cnt);
        }
        
        out.close();
    }
}