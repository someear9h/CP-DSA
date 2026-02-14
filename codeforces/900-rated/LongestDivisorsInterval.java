import java.io.*;

public class LongestDivisorsInterval {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try(var out =new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine());

            while(t-->0) {
                long n = Long.parseLong(br.readLine());

                int i = 1; 
                while(n % i == 0) {
                    i++;
                }

                out.println(i-1);
            }
        }


    }
}
