public class MinimumBitFlipstoConvertNumber {
    public static int minBitFlips(int start, int goal) {
        int cnt = 0;

        int xor = start ^ goal;

        while(xor > 0) {
            cnt += (xor & 1);
            xor = xor >> 1;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int start = 10;
        int goal = 7;

        System.out.println(minBitFlips(start, goal));
    }
}
