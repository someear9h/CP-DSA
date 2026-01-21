import java.util.Scanner;

public class D {
    // Array to store combinations (Pascal's Triangle)
    // C[n][k] will store "n choose k"
    static int[][] C = new int[31][31];

    public static void main(String[] args) {
        // 1. Precompute Combinations using Dynamic Programming
        // Formula: C(n, k) = C(n-1, k-1) + C(n-1, k)
        for (int i = 0; i < 30; i++) {
            C[i][0] = 1; // n choose 0 is always 1
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i-1][j-1] + C[i-1][j];
            }
        }

        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int t = sc.nextInt(); // Number of test cases
            while (t-- > 0) {
                int n = sc.nextInt();
                int k = sc.nextInt();

                // 2. Find d such that n = 2^d
                // Since n is a power of 2, we just count divisions by 2
                int d = 0;
                int tempN = n;
                while (tempN > 1) { // n=4 -> 2 -> 1 (d=2)
                    tempN /= 2;
                    d++;
                }

                int ans = 0;

                // 3. Iterate through all numbers less than n
                // maxBit is the position of the Most Significant Bit (0 to d-1)
                for (int maxBit = 0; maxBit < d; maxBit++) {
                    // cntBit is the number of set bits (1 to maxBit + 1)
                    for (int cntBit = 1; cntBit <= maxBit + 1; cntBit++) {
                        
                        // If moves required > k, Bob wins (it's a safe number)
                        if (maxBit + cntBit > k) {
                            // Add the number of ways to form such a number
                            // We fixed the MSB, so we choose (cntBit - 1) bits 
                            // from the remaining (maxBit) positions.
                            ans += C[maxBit][cntBit - 1];
                        }
                    }
                }

                // 4. Check the number n itself (which is 2^d)
                // For 2^d: maxBit is d, set bits is 1. Cost is d + 1.
                if (d + 1 > k) {
                    ans++;
                }

                System.out.println(ans);
            }
        }
        sc.close();
    }
}