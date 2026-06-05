import java.util.*;

public class A3945 {
    static int digitFrequencyScore(int n) {
        long sum = 0;

        int temp = n;

        Map<Integer, Integer> mp =new HashMap<>();
        
        while(temp > 0) {
            int dig = temp % 10;
            mp.put(dig, mp.getOrDefault(dig, 0) +1);
            temp /= 10;
        }
    
        for(Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            int k = entry.getKey();
            int v = entry.getValue();

            sum +=k * v;
        }

        return (int)sum;
    }

    public static void main(String[] args) {
        int n = 122;
        System.out.println(digitFrequencyScore(n));
    }
}
