public class DivideTwoIntegers {
    static int divide(int dividend, int divisor) {
        // The result of this operation is 2^31, which overflows Integer.MAX_VALUE.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        if(dividend == divisor) return 1;

        boolean pos = true;
        if(dividend >= 0 && divisor < 0) pos = false;
        if(dividend <= 0 && divisor > 0) pos = false;

        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long ans = 0;

        while(n >= d) {
            int cnt = 0;

            while(n >= (d << (cnt + 1))) {
                cnt++;
            }

            ans += 1L << cnt; // Use 1L to ensure the shift operates on a long
            n = n - (d << cnt);
        }

        return pos ? (int)ans : (int)-ans;
    }

    public static void main(String[] args) {
        int dividend = 10, divisor = 3;

        System.out.println(divide(dividend, divisor));
    }
}
