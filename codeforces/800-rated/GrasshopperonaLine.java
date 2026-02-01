/**
someear1h 
*/
 
import java.io.*;
import java.util.*;

public class GrasshopperonaLine {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                var st = new StringTokenizer(br.readLine());
                
                int x = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                if(x % k != 0) {
                    out.println("1");
                    out.println(x);
                } else {
                    out.println("2");
                    out.println(x-1+ " " + "1");
                }
            }
        }
    }
}