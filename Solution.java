// import java.util.*;
import java.io.*;

public class Solution {
    static int[] swapTwoNumbers(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        return new int[] {a, b};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try(PrintWriter out = new PrintWriter(System.out)) {
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());

            out.println("a: " + a);
            out.println("b: " + b);
            
            int[] arr = swapTwoNumbers(a, b);
            out.println();

            out.println("a: " + arr[0]);
            out.println("b: " + arr[1]);
        }
    }
}