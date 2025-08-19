// make ith bit 0

public class ClearIthBit {
    public static void main(String[] args) {
        int N = 13;
        int i = 2;

        System.out.println("N: " + Integer.toBinaryString(N));

        // Step 1: Create a temporary mask with a '1' at the i-th position.
        // This is the same first step as checking or setting a bit.
        // For i=2, (1 << 2) results in the number 4 (Binary: 0100).
        int bitmask = 1 << i;


        // Step 2: Invert the temporary mask using the bitwise NOT (~) operator.
        // This flips all the bits. 0100 becomes 1111...1011.
        // This final mask has a '0' only at the position we want to clear.
        int negMask = ~bitmask;

        // Step 3: Use the bitwise AND (&) operator with the final mask.
        // The '0' in the mask will force the i-th bit of N to become 0.
        // The '1's everywhere else in the mask will preserve all of N's other bits.
        //
        //    1101  (N = 13)
        //  & 1011  (finalMask, showing only the relevant bits)
        //  -------
        //    1001  (Result is 9)
        //
        // As you can see, the 2nd bit was successfully "cleared" or "turned off".
        int res = N & negMask;

        System.out.println("res: " + Integer.toBinaryString(res));
    }
}
