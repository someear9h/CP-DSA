import java.util.*;

public class MinimumJumpstoReachEndviaPrimeTeleportation {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) 
            return 0;
        
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) 
                maxVal = num;
        }
        
        int[] spf = buildSPF(maxVal);
        boolean[] isPrime = identifyPrimes(maxVal, spf);
        Set<Integer> appearingPrimes = getAppearingPrimes(nums, isPrime);
        Map<Integer, List<Integer>> primeToIndices = buildPrimeToIndicesMap(nums, appearingPrimes, spf);
        
        return bfs(nums, primeToIndices, isPrime);
    }

    private int[] buildSPF(int max) {
        int[] spf = new int[max + 1];
        for (int i = 2; i <= max; i++) {
            if (spf[i] == 0) {
                for (int j = i; j <= max; j += i) {
                    if (spf[j] == 0) {
                        spf[j] = i;
                    }
                }
            }
        }
        return spf;
    }

    private boolean[] identifyPrimes(int maxVal, int[] spf) {
        boolean[] isPrime = new boolean[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            isPrime[i] = (spf[i] == i);
        }
        return isPrime;
    }

    private Set<Integer> getAppearingPrimes(int[] nums, boolean[] isPrime) {
        Set<Integer> appearingPrimes = new HashSet<>();
        for (int num : nums) {
            if (num >= 2 && isPrime[num]) {
                appearingPrimes.add(num);
            }
        }
        return appearingPrimes;
    }

    private Map<Integer, List<Integer>> buildPrimeToIndicesMap(int[] nums, Set<Integer> appearingPrimes, int[] spf) {
        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();
        for (int p : appearingPrimes) {
            primeToIndices.put(p, new ArrayList<>());
        }
        
        for (int j = 0; j < nums.length; j++) {
            int num = nums[j];
            if (num == 1) 
                continue;
            
            Set<Integer> factors = getPrimeFactors(num, spf);
            for (int p : factors) {
                if (appearingPrimes.contains(p)) {
                    primeToIndices.get(p).add(j);
                }
            }
        }
        return primeToIndices;
    }

    private Set<Integer> getPrimeFactors(int val, int[] spf) {
        Set<Integer> factors = new HashSet<>();
        while (val > 1) {
            int prime = spf[val];
            factors.add(prime);
            while (val % prime == 0) {
                val /= prime;
            }
        }
        return factors;
    }

    private int bfs(int[] nums, Map<Integer, List<Integer>> primeToIndices, boolean[] isPrime) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0] = true;
        
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int idx = node[0];
            int steps = node[1];
            
            if (idx == n - 1) {
                return steps;
            }
            
            if (idx + 1 < n && !visited[idx + 1]) {
                visited[idx + 1] = true;
                queue.offer(new int[]{idx + 1, steps + 1});
            }
            
            if (idx - 1 >= 0 && !visited[idx - 1]) {
                visited[idx - 1] = true;
                queue.offer(new int[]{idx - 1, steps + 1});
            }
            
            int numHere = nums[idx];
            if (numHere >= 2 && isPrime[numHere] && primeToIndices.containsKey(numHere)) {
                List<Integer> indices = primeToIndices.get(numHere);
                for (int j : indices) {
                    if (j == idx) 
                        continue;
                    if (!visited[j]) {
                        visited[j] = true;
                        queue.offer(new int[]{j, steps + 1});
                    }
                }
                primeToIndices.remove(numHere);
            }
        }
        
        return -1;
    }
}