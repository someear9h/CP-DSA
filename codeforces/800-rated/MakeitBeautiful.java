/**
someear1h 
*/

import java.io.*;
import java.util.*;

public class MakeitBeautiful {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                int n = Integer.parseInt(br.readLine());

                var st = new StringTokenizer(br.readLine());
                int[] a = new int[n];

                for(int i = 0; i < n; i++){
                    a[i] = Integer.parseInt(st.nextToken());
                }

                Arrays.sort(a);
                int mx = a[n-1];
                int mn = a[0];

                if(mx == mn) {
                    out.println("NO");
                } else {
                    out.println("YES");
                    out.print(a[n-1] + " ");
                    for(int i = 0; i < n-1; i++) {
                        out.print(a[i] + " ");
                    }

                    out.println();
                }
            }
        }
    }
}
