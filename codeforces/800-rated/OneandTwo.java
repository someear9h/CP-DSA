/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class OneandTwo {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                var st = new StringTokenizer(br.readLine());
                int[] a = new int[n];

                int twos = 0;
                for(int i = 0; i < n; i++) {
                    a[i] = Integer.parseInt(st.nextToken());
                    if(a[i] == 2) twos++;
                }
                
                if(twos == 0) {
                    out.println("1");
                    continue;
                } 


                if(twos % 2 != 0) {
                    out.println("-1");
                } else {
                    int target = twos /2;
                    int tt = 0;

                    for(int i = 0; i < n; i++) {
                        if(a[i] == 2) {
                            tt++;
                        }

                        if(tt == target) {
                            out.println(i+1);
                            break;
                        }
                    }
                }
            }
        }
    }
}