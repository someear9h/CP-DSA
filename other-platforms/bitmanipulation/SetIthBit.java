// make ith bit 1

public class SetIthBit {
    public static void main(String[] args) {
        int N = 9;
        int i = 2;

        System.out.println(N + ", Binary: " + Integer.toBinaryString(N));

        // Step 1: Create a mask. This is the same first step as checking a bit.
        // We create a number that has a '1' only at the i-th position.
        // For i=2, (1 << 2) results in the number 4 (Binary: 0100).
        int bitmask = 1 << i;

        // Step 2: Use the bitwise OR (|) operator.
        // The OR operator will keep all of N's original bits, but because the mask
        // has a '1' at the i-th position, the result is guaranteed to have a '1'
        // at that position as well.
        //
        //    1001  (N = 9)
        //  | 0100  (mask)
        //  -------
        //    1101  (Result is 13)
        //
        // As you can see, the 2nd bit was successfully "turned on".

        int res = N | bitmask;

        System.out.println(res + ", Binary: " + Integer.toBinaryString(res));
    }    
}
