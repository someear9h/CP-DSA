import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while(t--> 0) {
            var st = new StringTokenizer(br.readLine());
            
            Long s = Long.parseLong(st.nextToken());
            Long k = Long.parseLong(st.nextToken());
            Long m = Long.parseLong(st.nextToken());

            long flips = m/k;
            long rem = m%k;
            long ans = 0;
            if(s <= k) {
                ans = Math.max(0, s - rem);
            } else {
                if(flips % 2 == 0) {
                    ans = s - rem;
                } else {
                    ans = k - rem;
                }
            }

            System.out.println(ans);
        }
        
    }
}

/*
 * -------------------------------------------------------------------------------------
 * PROBLEM: HOURGLASS (Simulation / Math)
 * -------------------------------------------------------------------------------------
 * INTUITION:
 * The hourglass state depends on whether the sand runs out before the next flip.
 * We calculate the state at time 'm' using modulo arithmetic.
 *
 * VARIABLES:
 * - intervals = m / k (Number of flips performed)
 * - rem = m % k (Time passed in the current interval)
 *
 * CASE 1: s <= k (Small Hourglass)
 * - The sand always finishes falling before the next flip.
 * - At the end of every interval, the bottom catches 's' sand.
 * - Therefore, EVERY new interval starts with 's' sand at the top.
 * - Ans = max(0, s - rem)
 *
 * CASE 2: s > k (Large Hourglass)
 * - The sand never runs out completely in one interval (since k < s).
 * - Interval 0: Starts with 's'. Bottom collects 'k'.
 * - Interval 1: Flip! Starts with 'k' (from bottom). Bottom collects 's'.
 * - Interval 2: Flip! Starts with 's'.
 * - PATTERN:
 * - Even Intervals (0, 2, 4...): Start with 's'.
 * - Odd Intervals (1, 3, 5...): Start with 'k'.
 * - Ans = StartCapacity - rem
 * -------------------------------------------------------------------------------------
 */
