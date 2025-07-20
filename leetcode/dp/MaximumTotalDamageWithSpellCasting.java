import java.util.*;

public class MaximumTotalDamageWithSpellCasting {
    public static long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> freq = new HashMap<>();
        
        // Step 1: Frequency map
        for (int p : power) {
            freq.put(p, freq.getOrDefault(p, 0) + 1);
        }

        // Step 2: Extract and sort unique damages
        List<Integer> unique = new ArrayList<>(freq.keySet());
        Collections.sort(unique);

        // Step 3: Initialize DP array
        long[] dp = new long[unique.size()];
        Arrays.fill(dp, -1);

        // Step 4: Start recursion from the last unique damage
        return f(unique.size() - 1, freq, unique, dp);
    }

    private static long f(int idx, Map<Integer, Integer> freq, List<Integer> unique, long[] dp) {
        // Base case: If no spells left, return 0
        if (idx < 0) return 0;

        // If already computed, return the stored result
        if (dp[idx] != -1) return dp[idx];

        int currDamage = unique.get(idx);
        long take = (long) currDamage * freq.get(currDamage);

        // Find the largest j where unique[j] < currDamage - 2
        int prevIdx = idx - 1;
        while (prevIdx >= 0 && unique.get(prevIdx) >= currDamage - 2) {
            prevIdx--;
        }
        take += f(prevIdx, freq, unique, dp);

        long notTake = f(idx - 1, freq, unique, dp);

        dp[idx] = Math.max(take, notTake);
        return dp[idx];
    }

    public static void main(String[] args) {
        int[] power = {1,1,3,4};

        System.out.println(maximumTotalDamage(power));
    }
}
