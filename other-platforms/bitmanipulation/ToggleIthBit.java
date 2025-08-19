public class ToggleIthBit {
    public static void main(String[] args) {
        int N = 13;
        int i = 2; // i = 1 for toggling 0 to 1

        System.out.println("N: " + Integer.toBinaryString(N));

        // --- The "Toggle the i-th bit" Algorithm ---

        // Step 1: Create a mask with a '1' at the i-th position.
        // This is the same first step as all the other bitwise operations.
        // For i=2, (1 << 2) results in the number 4 (Binary: 0100).
        int mask = 1 << i;

        // Step 2: Use the bitwise XOR (^) operator.
        // XORing with the mask will flip the bit at the i-th position because that's
        // where the mask's '1' is. All other bits in N will be XORed with 0,
        // which leaves them unchanged.
        //
        //    1101  (N = 13)
        //  ^ 0100  (mask)
        //  -------
        //    1001  (Result is 9)
        //
        // As you can see, the 2nd bit, which was a '1', has been toggled to a '0'.

        int res = N ^ mask;
        System.out.println("res: " + Integer.toBinaryString(res));
    }    
}
