public class TotalWavinessofNumbersinRangeI {
    public static int totalWaviness(int num1, int num2) {
        int cnt = 0;
        for(int i = num1; i <= num2; i++) {
            cnt += solve(i);
        }

        return cnt;
    }

    private static int solve(int num) {
        String s = String.valueOf(num);
        int n = s.length();

        if(n < 3) return 0;

        int wav = 0;

        for(int i = 1; i < n - 1; i++) {
            char prev = s.charAt(i -1);
            char curr = s.charAt(i);
            char next = s.charAt(i+1);

            if(curr < prev && curr < next) {
                wav++;
            } else if(curr > prev && curr > next) {
                wav++;
            }
        }

        return wav;
    }

    public static void main(String[] args) {
        int num1 = 120;
        int num2 = 130;

        System.out.println(totalWaviness(num1, num2));
    }
}
