import java.io.*;
import java.util.*;

public class A {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t--> 0) {
            var st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int sum = 0;
            for(int a : arr) sum += a;
            
            if(sum <= s && ((s-sum) % x == 0)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }    
}
