import java.io.*;
import java.util.*;

public class A {
   public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                
                int n = Integer.parseInt(br.readLine());
                
                long[] arr = new long[n];
                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int i = 0; i < n; i++) {
                    arr[i] = Long.parseLong(st.nextToken());
                }

                Arrays.sort(arr);
                out.println(arr[n - 1]);
            }
        }
    }
}
