package codeforces.contests.Codeforces_Round_1098_Div2;

// author: someear1h

// same code also solved C2

/**
 * REVISION SNAPSHOT:
 * 1. Class: Defensive Array Gating / Digit Boundary Optimization
 * 2. Key Insight: When hardcoding lookaheads like d[1] for structural modifications, 
 * always gate the logic with size checks (d.length > 1) to prevent minimalist array crashes.
 * 3. Signal: Edge cases where an alphabet array shrinks to 1 element (like d = [0]).
*/

import java.io.*;
import java.util.*;

public class C1 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var out = new PrintWriter(System.out);
        var st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        while(t-- > 0) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            long[] d = new long[n];

            for(int i = 0; i < n; i++) {
                d[i] = Long.parseLong(st.nextToken());
            }

            out.println(solve(a, d));
        }

        out.flush();
    }

    static long solve(long a, long[] d) {
        String sA = String.valueOf(a);
        int len = sA.length();

        List<Long> candi = new ArrayList<>();
        for(long di : d) {
            if(di == 0) {
                candi.add(di);
                break;
            }
        }

        long smallest = d[0];
        long largest = d[d.length-1];

        // look for b = less length than a
        // we take the highest number from d
        if(len > 1) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < len-1; i++) sb.append(largest);

            candi.add(Long.parseLong(sb.toString()));
        }

        // look for b = 1 greater than length of a
        // we take the smallest element from d
        StringBuilder sb = new StringBuilder();
        if(smallest > 0) {
            for(int i = 0; i < len+1; i++) sb.append(smallest);
            candi.add(Long.parseLong(sb.toString()));
        } 
        //
        else if(d.length > 1){
            // if smallest is 0, we take the second smallest
            // and then append the smallest element which is 0

            sb.append(d[1]);
            for(int i = 0; i < len; i++) {
                sb.append(smallest);
            }

            candi.add(Long.parseLong(sb.toString()));
        }

        // now go for matching prefix where b is 
        // same length as a
        for(int p = 0; p <= len; p++) {
            if(!isValidPrefix(sA, p, d)) continue;

            if(p == len) {
                candi.add(a);
                continue;
            }

            int currDigit = sA.charAt(p) - '0';

            // find ceil number
            // look for a greater number than currDigit in d
            long ceilDigit = -1;
            for(long di : d) {
                if(di > currDigit) {
                    ceilDigit = di;
                    break;
                }
            }

            if(ceilDigit != -1) {
                StringBuilder sbA = new StringBuilder();
                sbA.append(sA, 0, p); // gets sA string and appends from index 0 to p-1
                sbA.append(ceilDigit);
                for(int i = p + 1; i < len; i++) {
                    sbA.append(smallest);
                }

                long val = Long.parseLong((sbA.toString()));
                candi.add(val);
            }

            // find the floor number
            // look for greatest number smaller than currDigit in d
            long floorDigit = -1;
            for(int i =d.length - 1; i >= 0; i--) {
                if(d[i] < currDigit) {
                    floorDigit = d[i];
                    break;
                }
            }

            if(floorDigit != -1) {
                StringBuilder sbA = new StringBuilder();
                sbA.append(sA, 0, p);
                sbA.append(floorDigit);

                for(int i = p+1; i < len; i++) {
                    sbA.append(largest);
                }

                long val = Long.parseLong((sbA.toString()));
                candi.add(val);
            }
        }

        long mn = Long.MAX_VALUE;
        for(long cand : candi) {
            mn = Math.min(mn, Math.abs(cand - a));
        }

        return mn;
    }

    static boolean isValidPrefix(String sA, int p, long[] d) {
        for(int i = 0; i < p; i++) {
            int digit = sA.charAt(i) - '0';
            boolean f = false;

            for(long di : d) {
                if(digit == di) {
                    f = true;
                    break;
                }
            }

            if(!f) return false;
        }

        return true;
    }
}