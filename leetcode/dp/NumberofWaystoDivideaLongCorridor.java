import java.util.ArrayList;
import java.util.List;

public class NumberofWaystoDivideaLongCorridor {
    static int mod = (int)1e9 + 7;

    public static int numberOfWays(String corridor) {
        char[] corr = corridor.toCharArray();
        int n = corr.length;

        List<Integer> seats = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(corr[i] == 'S') seats.add(i);
        }

        if(seats.size() < 2 || seats.size() % 2 == 1) return 0;

        long ways = 1;
        for(int i = 2; i < seats.size(); i += 2) {
            int prevEnd = seats.get(i - 1);
            int next = seats.get(i);

            int plantsBetween = next - prevEnd - 1;

            ways = (ways * (plantsBetween + 1)) % mod;
        }

        return (int)ways;
    }

    public static void main(String[] args) {
        String corridor = "SSPPSPS";

        System.out.println(numberOfWays(corridor));
    }
}
