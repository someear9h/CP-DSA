/**
someear1h 
*/
 
import java.io.*;

public class GamewithIntegers {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());

                if(n % 3 == 0) out.println("Second");
                else out.println("First");
            }
        }
    }
}