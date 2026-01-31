/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class Buttons {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                var st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                if(c % 2 == 0) {
                    if(a > b) out.println("First");
                    else out.println("Second");
                } else {
                    if(b > a) out.println("Second");
                    else out.println("First");
                }
            }
        }
    }
}