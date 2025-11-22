public class MinimumNumberofFlipstoReverseBinaryString {
    public static int minimumFlips(int n) {
        if(n == 1) return 0;
        String bN = Integer.toBinaryString(n);

        String revbN = new StringBuilder(bN).reverse().toString();

        int revNum = Integer.parseInt(revbN, 2);

        int res = revNum ^ n;

        int cnt = 0; // count no of set bits in res
        while(res > 0) {
            res = res & (res - 1);
            cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int n1= 7;
        int n2 = 10;
        System.out.println(minimumFlips(n1));
        System.out.println(minimumFlips(n2));
    }
}
