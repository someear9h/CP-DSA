public class RemoveLastSetBit {

    public static void main(String[] args) {
        // We want to remove the rightmost '1' from its binary representation.
        int N = 40; // Binary: 101000
        
        System.out.println("Original Number N = " + N + " (Binary: " + Integer.toBinaryString(N) + ")");
        System.out.println("Removing the rightmost set bit...");

        // --- The "Remove the Last Set Bit" Algorithm ---

        // The key insight is what happens when you subtract 1 from a number.
        // Subtracting 1 flips the rightmost '1' to a '0', and all the '0's
        // to its right are flipped to '1's.
        //
        // N      = 40  ->  101000
        // N - 1  = 39  ->  100111
        //
        // Notice how the rightmost '1' and the bits after it are flipped.

        // Now, we use the bitwise AND (&) operator with N and (N-1).
        //
        //    101000  (N = 40)
        //  & 100111  (N - 1 = 39)
        //  --------
        //    100000  (Result is 32)
        //
        // The AND operation effectively cancels out the rightmost '1' while
        // preserving all the bits to its left.
        int result = N & (N - 1);

        System.out.println("\nResult of (N & (N-1)) = " + result + " (Binary: " + Integer.toBinaryString(result) + ")");
    }
}
