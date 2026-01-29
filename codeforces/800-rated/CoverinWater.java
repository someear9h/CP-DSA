/**
someear1h 
*/
 
import java.io.*;

public class CoverinWater {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());

                String s = br.readLine();
                
                if(s.contains("...")) {
                    out.println(2);
                }

                else {
                    int dots = 0;
                    for(int i = 0; i < n; i++) {
                        if(s.charAt(i) == '.') dots++;
                    }   
                    out.println(dots);
                }

            }
        }
    }
}