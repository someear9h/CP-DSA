package codeforces.contests.Codeforces_Round_1101_Div2;

// author: someear1h

import java.util.*;
import java.io.*;

public class C1 {

    static int tryM(String s, int m, int tables, int seats) {
        int currTables = 0;
        int seated = 0;

        for(char ch : s.toCharArray()) {
            if(ch == 'I') {
                if(currTables < tables) {
                    seated++;
                    currTables++;
                }
            }

            else if(ch == 'E') {
                if(seated < currTables * seats)
                    seated++;
            }

            else {
                if(m > 0) {
                    if(currTables < tables) {
                        seated++;
                        currTables++;
                    }
                    
                    m--;

                } else {
                    if(seated < currTables * seats) 
                        seated++;
                }
            }
        }

        return seated;
    }
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);
        var st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        while(t-->0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int sa = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            int tables = x;
            int seats = sa;

            int totalA = 0;
            for(int i = 0; i < n; i++) {
                if(s.charAt(i) == 'A') totalA++;
            }

            int friends = -1;

            // m -> total number of As acting as opener
            // we try every such number and take optimal answer
            for(int m = 0; m <= totalA; m++) {
                friends = Math.max(friends, tryM(s, m, tables, seats));
            }

            out.println(friends);
        }

        out.close();
    }
}
