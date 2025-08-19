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
            cnt += (n & 1);

            // Step 2: Shift the number to the right.
            // The right shift operator '>>' discards the last bit we just checked
            // and moves all other bits one position to the right.
            // This prepares the next bit to be checked in the next iteration.
            n = n >> 1;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int n = 13;
        System.out.println(countSetBits(n));
    }
}
