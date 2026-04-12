/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            var st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());

            while(t-- > 0) {
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());

                st = new StringTokenizer(br.readLine());
                long[] a = new long[n];
                for(int i = 0; i < n; i++) {
                    a[i] = Long.parseLong(st.nextToken());
                }
                
            }
        }
    }
}