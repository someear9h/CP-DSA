import java.io.*;
import java.util.*;

public class A {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            
            while(t--> 0) {
                int n = Integer.parseInt(br.readLine());
                var st = new StringTokenizer(br.readLine());
                
                int[] arr = new int[n];
                for(int i = 0; i < n; i++) {
                    arr[i] = Integer.parseInt(st.nextToken());
                }

                out.println(solve(arr, n));
            }
        }
    }

    static String solve(int[] arr, int n) {
        if(arr[0] == 1 || arr[n-1] == 1) return "alice";
        else return "bob";
    }
}
