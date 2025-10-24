import java.io.*;
import java.util.*;

public class Solution {
    static void solve(int[] arr, int n) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve(arr, n);

        for(int a : arr) {
            out.print(a + " ");
        }

        out.close();
    }
}