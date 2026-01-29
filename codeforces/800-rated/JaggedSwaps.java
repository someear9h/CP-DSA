/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class JaggedSwaps {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());

                var st = new StringTokenizer(br.readLine());
                int[] a = new int[n];

                for(int i = 0; i < n; i++) {
                    a[i] = Integer.parseInt(st.nextToken());
                }

                // for a permutation, a sorted array must have 1 at index 1 and n at index n
                // since we cant swap index 1 with anything, if there is 1 at index 1 we can sort
                // if a[1] = 1 -> yes sorting possible

                if(a[0] == 1) {
                    out.println("YES");
                } else {
                    out.println("NO");
                }
            }
        }
    }
}