package codeforces.contests.Codeforces_Round_1098_Div2;

// author: someear1h

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);
        var st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        while(t-- > 0) {
            st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long x1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            
            if(n <= 3) {
                out.println("1");
            }
            else {

                out.println(Math.min(Math.abs(x1 - x2), n - Math.abs(x1-x2)) + k);
            }
        }

        out.flush();
    }
}