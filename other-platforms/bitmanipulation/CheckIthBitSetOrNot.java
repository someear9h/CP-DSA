// check if ith bit is set (1) or not (0)
// time complexity and space complexity for both methods = O(1)

public class CheckIthBitSetOrNot {
    private static void checkWithLeftShift(int N, int i) {
        // Use the bitwise AND (&) operator.
        // The AND operator compares two numbers bit by bit. The result's bit is 1
        // only if the corresponding bits in both numbers are 1.
        //
        //    1101  (N = 13)
        //  & 0100  (mask) 1 << i means 1 -> 10 -> 100
        //  -------
        //    0100  (Result is 4, which is not 0)
        //
        // If the result is not zero, it means the bit at the mask's position was 1 in N.

        if((N & (1 << i)) != 0) {
            System.out.println("The Bit is Set");
        } else {
            System.out.println("Bit is not set");
        }
    }

    private static void checkWithRightShift(int N, int i) {
        // Step 2: Use the bitwise AND (&) operator with 1.
        // Now that our bit of interest is at the end, we can check if it's a 1
        // by ANDing it with the number 1 (Binary: 0001).
        //
        //    0011  (shiftedN)
        //  & 0001  (1)
        //  -------
        //    0001  (Result is 1, which is not 0)
        //
        // If the last bit of shiftedN is 1, the result will be 1. If it's 0, the result will be 0.

        if(((N >> i) & 1) != 0) {
            System.out.println("The Bit is Set");
        } else {
            System.out.println("Bit is not set");
        }
    }

    public static void main(String[] args) {
        int N = 13;
        int i = 2; // i = 1 for not set bit

        checkWithLeftShift(N, i);

        checkWithRightShift(N, i);
    }
}
