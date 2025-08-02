import java.util.*;

public class MinimumJumpstoReachEndviaPrimeTeleportation {

    public static int minJumps(int[] nums) {
        int MAX = 1000000;
        int[] spf = buildSPF(MAX); // Smallest Prime Factor sieve

        int n = nums.length;
        Map<Integer, List<Integer>> primeMap = new HashMap<>();

        // Step 1: Build map of prime factors to indices
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            Set<Integer> primes = getPrimeFactors(val, spf); // Unique primes
            for (int p : primes) {
                primeMap.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
            }
        }

        boolean[] visited = new boolean[n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int idx = curr[0], steps = curr[1];

            if (idx == n - 1) return steps;

            // Jump to i - 1 and i + 1
            if (idx + 1 < n && !visited[idx + 1]) {
                visited[idx + 1] = true;
                q.add(new int[]{idx + 1, steps + 1});
            }
            if (idx - 1 >= 0 && !visited[idx - 1]) {
                visited[idx - 1] = true;
                q.add(new int[]{idx - 1, steps + 1});
            }

            // Jump to all indices sharing prime factors
            Set<Integer> factors = getPrimeFactors(nums[idx], spf);
            for (int prime : factors) {
                if (primeMap.containsKey(prime)) {
                    for (int nextIdx : primeMap.get(prime)) {
                        if (!visited[nextIdx]) {
                            visited[nextIdx] = true;
                            q.add(new int[]{nextIdx, steps + 1});
                        }
                    }
                    // Remove after visiting to avoid future checks
                    primeMap.remove(prime);
                }
            }
        }
        return -1;
    }

    // Function 1: Build Smallest Prime Factor sieve
    private static int[] buildSPF(int max) {
        int[] spf = new int[max + 1];
        for (int i = 2; i <= max; i++) {
            if (spf[i] == 0) {
                for (int j = i; j <= max; j += i) {
                    if (spf[j] == 0) spf[j] = i;
                }
            }
        }
        return spf;
    }

    // Function 2: Prime Factorization using SPF
    private static Set<Integer> getPrimeFactors(int x, int[] spf) {
        Set<Integer> result = new HashSet<>();
        while (x > 1) {
            int p = spf[x];
            result.add(p);
            while (x % p == 0) x /= p;
        }
        return result;
    }
}
