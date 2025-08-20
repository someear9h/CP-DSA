public class CheckIfNumberIsPowerOfTwo {
    public static void main(String[] args) {
        int N = 16; // N = 13 is not a power of 2

        if (N <= 0) {
            System.out.println("Result: No (Number must be positive).");
            return;
        }

        // --- The "Check for Power of 2" Algorithm ---
        // the fact that (N & (N-1)) removes the rightmost set bit.
        //
        // Example 1 (N = 16):
        //    10000  (N = 16)
        //  & 01111  (N - 1 = 15)
        //  -------
        //    00000  (Result is 0)
        //
        // Example 2 (N = 13):
        //    1101  (N = 13)
        //  & 1100  (N - 1 = 12)
        //  -------
        //    1100  (Result is 12, which is not 0)
        //
        // If the result is 0, it means N had only one set bit to begin with.

        if((N & N - 1) == 0) {
            System.out.println("Number is power of 2");
        } else {
            System.out.println("Not a power of 2");
        }
    }
}
