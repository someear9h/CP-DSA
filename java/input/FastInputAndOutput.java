import java.io.*;
import java.util.*;

public class FastInputAndOutput {
    public static void main(String[] args) throws IOException {
        // Initialize fast input reader
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));

        //Initialize fast output writer
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // read an integer array 
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // read a string
        String str = br.readLine();

        // read two integers
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());


        // print
        out.print("Array: ");
        for(int i = 0; i < n; i++) {
            out.print(arr[i] + " ");
        }
        out.println();

        out.println("String: " + str);

        out.println("Two integers are: " + a + " and " + b);

        out.flush();
    }
}