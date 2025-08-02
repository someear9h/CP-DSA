import java.io.*;

public class ChewbacaandNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String s = br.readLine();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            int inverted = 9 - digit;

            // dont invert when we are at first index and inverted is zero
            if(i == 0 && inverted == 0) {
                sb.append(digit);
            } else {
                sb.append(Math.min(digit, inverted));
            }
        } 


        out.println(sb.toString());
        out.flush();
    }    
}
