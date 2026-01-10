import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try (PrintWriter out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            
            while(t-->0) {
                var st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                
                if(n <= 2) out.println(1);
                else {
                    int curr = 2;
                    int flo = 1;

                    while(curr < n) {
                        curr+=x;
                        flo++;
                    }

                    out.println(flo);
                }
            }
        }
    }
}