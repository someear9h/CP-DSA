package codeforces.contests.Codeforces_Round_1093_Div2;

/**
 * author: someear1h 
*/

/**
 * Class: Contiguous Block / Pigeonhole Principle.
 * Key Insight: Movement speed limits (1 unit/sec) combined with cyclic blocking (mod $m$) 
 * means any blocked region wider than the cycle length is impassable.
 * Signal: Timer-based obstacles on a 1D line with a maximum movement speed.
 * 
*/

import java.io.*;
import java.util.*;

public class B_OIE_Excursion {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            var st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());

            while(t-->0) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                long m = Long.parseLong(st.nextToken());

                st = new StringTokenizer(br.readLine());
                long[] a = new long[n];
                for(int i = 0; i < n; i++) {
                    a[i] = Long.parseLong(st.nextToken());
                }

                out.println(solve(a, n, m));
            }
        }
    }

    static String solve(long[] a, int n, long m) {
        boolean canPass = true;
        int i = 0;
        while(i < n) {
            int j = i;
            while(j < n && a[j] == a[i]) {
                j++;
            }

            int blockLen = j -i;
            if(blockLen >= m) {
                canPass = false;
                break;
            }

            i = j;
        }

        return canPass ? "YES" : "NO";
    }
}