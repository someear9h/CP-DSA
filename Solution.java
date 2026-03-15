/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine().trim());
            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine().trim());
                var st = new StringTokenizer(br.readLine().trim());
                long[] a = new long[n];
                for(int i = 0; i < n; i++) {
                    a[i] = Long.parseLong(st.nextToken());
                }
                


            }
        }
    }
}