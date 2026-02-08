/**
someear1h 
*/
 
import java.io.*;

public class PrependandAppend {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());
            while(t-- > 0) {
                int n = Integer.parseInt(br.readLine());
                String s = br.readLine();

                int l = 0, r =n-1;
                boolean found = false;
                while(l <=r) {
                    if((s.charAt(l) == '0' && s.charAt(r) == '1')
                    || (s.charAt(l) == '1' && s.charAt(r) == '0')) {
                        l++;
                        r--; 
                    } else {
                        out.println(r-l+1);
                        found = true;
                        break;
                    }
                }

                if(!found) {
                    out.println(0);
                }
            }
        }
    }
}