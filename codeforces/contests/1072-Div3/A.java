import java.io.*;

public class A {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while(t--> 0) {
            int n = Integer.parseInt(br.readLine());

            if(n == 2) System.out.println(2);
            else if(n == 3) System.out.println("3");
            else if(n % 2 == 0) System.out.println("0");
            else System.out.println("1");
        }
        
    }
}