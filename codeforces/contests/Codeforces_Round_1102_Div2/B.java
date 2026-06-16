package codeforces.contests.Codeforces_Round_1102_Div2;

// author: someear1h

import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        var br =new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);
        var st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());

            long rem = n % 12;

            if(rem == 10) {
                if(n >= 22) {
                    out.println(22 + " " + (n-22));
                } else {
                    out.println("-1");
                }
            } else {
                out.println(n%12 + " " + (n - rem));
            }
        }
       
        out.close();
    }
}