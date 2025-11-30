public class ConcatenateNonZeroDigitsandMultiplybySumI {
    public static long sumAndMultiply(int n) {
        if(n == 0) return 0;
        String s = Integer.toString(n);
        StringBuilder sb = new StringBuilder();
        int sum = 0;

        for(char c : s.toCharArray()) {
            if(c != '0') {
                sb.append(c);
                sum = sum + (c - '0');
            }
        }

        int num = Integer.parseInt(sb.toString());
        return (long) sum * num;
    }

    public static void main(String[] args) {
        int n = 10203004;
        System.out.println(sumAndMultiply(n));
    }
}
