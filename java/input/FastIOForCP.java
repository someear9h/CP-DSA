import java.io.*;
import java.util.*;

public class FastIOForCP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        // PrintWriter out = new PrintWriter(System.out); you can also have this

        int t = Integer.parseInt(br.readLine()); // number of test cases

        while (t-- > 0) {
            String s = br.readLine(); // read string
            int n = Integer.parseInt(br.readLine()); // read integer

            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            int j = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken()); // taking input as spaced integer

            // Print everything for verification
            out.println("String: " + s);
            out.println("Integer: " + n);
            out.print("Array: ");

            out.println("two spaced integers: " + j + " " + k);
            for (int i = 0; i < n; i++) {
                out.print(arr[i] + " ");
            }
            out.println();
        }

        out.flush();
    }
}

/*
 * Takes t test cases,

For each test case:

Reads a String s

Reads an int n

Reads an array of n integers.
 */