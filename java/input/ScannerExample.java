import java.util.Scanner;

@SuppressWarnings("all")
public class ScannerExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // take n and array input
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // print the array
        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // read a full string line
        sc.nextLine();
        String str = sc.nextLine();

        // read a 2D array
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        int[][] twoDArr = new int[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                twoDArr[i][j] = sc.nextInt();
            }
        }

        // read n characters
        int charCount = sc.nextInt();
        char[] chars = new char[charCount];
        for(int i = 0; i < charCount; i++) {
            chars[i] = sc.next().charAt(0);
        }

        //  Read list of n strings
        int strCount = sc.nextInt();
        String[] stringList = new String[strCount];
        sc.nextLine(); // flush newline
        for (int i = 0; i < strCount; i++) {
            stringList[i] = sc.nextLine();
        }

        // 6. Read n pairs of integers
        int pairCount = sc.nextInt();
        int[][] pairs = new int[pairCount][2];
        for (int i = 0; i < pairCount; i++) {
            pairs[i][0] = sc.nextInt();
            pairs[i][1] = sc.nextInt();
        }

        // Output to verify (optional)
        System.out.println("\nArray:");
        for (int x : arr) System.out.print(x + " ");

        System.out.println("\n\nString:");
        System.out.println(str);

        System.out.println("\n2D Array:");
        for (int[] row : twoDArr) {
            for (int x : row) System.out.print(x + " ");
            System.out.println();
        }

        System.out.println("\nCharacters:");
        for (char c : chars) System.out.print(c + " ");

        System.out.println("\n\nString List:");
        for (String s : stringList) System.out.println(s);

        System.out.println("\nPairs:");
        for (int[] p : pairs) System.out.println(p[0] + " " + p[1]);
    }
}
