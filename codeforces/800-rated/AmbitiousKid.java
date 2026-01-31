/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class AmbitiousKid {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int n = Integer.parseInt(br.readLine());
            
            var st = new StringTokenizer(br.readLine());

            int[] arr = new int[n];
            int mn = Integer.MAX_VALUE;

            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                mn = Math.min(mn, Math.abs(arr[i]));
            }

            out.println(mn);
        }
    }
}