import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            
            while(t--> 0) {
                var st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                st = new StringTokenizer(br.readLine());
                
                int[] arr = new int[n];
                for(int i = 0; i < n; i++) {
                    arr[i] = Integer.parseInt(st.nextToken());
                }

                out.println(solve(arr, n, k));
            }
        }
    }

    static int solve(int[] arr, int n, int k) {
        int[] freq = new int[n+2];

        for(int a : arr) {
            if(a < freq.length) freq[a]++;
        }

        int mex = 0; // get the whole arrays mex
        while(freq[mex] > 0) {
            mex++;
        }

        return Math.min(mex, k-1);
    }
}
