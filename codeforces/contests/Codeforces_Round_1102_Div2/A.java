package codeforces.contests.Codeforces_Round_1102_Div2;

// author: someear1h

import java.io.*;
import java.util.*;

public class A {
    public static void main(String[] args) throws IOException {
        var br =new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);
        var st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            long[] b = new long[n];
            for(int i = 0; i < n; i++) {
                b[i] = Long.parseLong(st.nextToken());
            }

            Arrays.sort(b);
            if(n == 2) {
                out.println(b[n-1] + " " + b[n-2]);
            } else {

                if(solve(b, n)) {
                    out.println(b[n-1] + " " + b[n-2]);
                } else {
                    out.println("-1");
                }
            }   
        }
       
        out.close();
    }

    static boolean solve(long[] b, int n) {

        for(int i =n-1; i>= 2; i--) {
            long rem = b[i] % b[i-1];
            if(rem != b[i-2]) {
                return false;
            }
        }
        return true;
    }
}