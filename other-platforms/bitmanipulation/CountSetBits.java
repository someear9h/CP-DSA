public class CountSetBits {
    private static int countSetBits(int n) {
        int cnt = 0;

        // Loop as long as n is not zero. Each iteration processes one bit.
        while (n > 0) {
            // Step 1: Check the last bit.
            // The expression (n & 1) isolates the last bit.
            // If the last bit is 1, (n & 1) will be 1.
            // If the last bit is 0, (n & 1) will be 0.
            // We add this result (either 1 or 0) to our count.
            cnt += (n & 1); // n & 1 checks the odd number -> odd number has 1 as last bit, even has 0

            // Step 2: Shift the number to the right.
            // The right shift operator '>>' discards the last bit we just checked
            // and moves all other bits one position to the right.
            // This prepares the next bit to be checked in the next iteration.
            n = n >> 1; // equivalent to dividing by 2
        }

        return cnt;
    }
    // tc and sc = O(no of setbits), worst case -> O(log base 2 n)
    private static int countWithBrianKernighan(int n) {
        int cnt = 0;

        while(n > 0) {
            // The magic trick: n & (n - 1) removes the rightmost set bit.
            // The loop will run exactly as many times as there are set bits.
            //
            // Iteration 1: n = 13 (1101). n-1 = 12 (1100). 1101 & 1100 -> 1100 (12). count becomes 1.
            // Iteration 2: n = 12 (1100). n-1 = 11 (1011). 1100 & 1011 -> 1000 (8).  count becomes 2.
            // Iteration 3: n = 8  (1000). n-1 = 7  (0111). 1000 & 0111 -> 0000 (0).  count becomes 3.
            // n is now 0, so the loop terminates.
            n = n & (n - 1);
            cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int n = 13;
        System.out.println(countSetBits(n));
        System.out.println(countWithBrianKernighan(n));
    }
}
