import java.io.*;

public class FastInputExample {
    public static void main(String[] args) throws IOException {
        // Set up BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read 'n'
        int n = Integer.parseInt(br.readLine());

        // Read n space-separated integers
        int[] arr = new int[n];
        String[] parts = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }

        // Read a full string line
        String str = br.readLine();

        // Output to verify
        System.out.println("Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println("\nString:");
        System.out.println(str);
    }
}
