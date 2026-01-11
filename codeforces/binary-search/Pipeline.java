import java.io.*;
import java.util.*;

public class Pipeline {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        
        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        if(n == 1) {
            System.out.println(0);
            return;
        }
        // we get maxGain when used every splitter -> k - 1
        long maxGain = (k-1) * (k) /2;   
        if(maxGain < n-1) {
            System.out.println("-1");
            return;
        }

        // binary search
        // h = because max spliiters gain is k - 1
        long l = 1, h = k-1;
        long ans = -1;

        while(l <= h) {
            long mid = l + (h-l) / 2;
            // check if used mid number of splitters can we get the gain we want?
            // if yes search for lower number of splitters
            long gain = getGain(mid, k);
            if(gain >= n - 1) {
                ans = mid;
                h = mid - 1;
            } else {
                l = mid+1;
            }
        }

        System.out.println(ans);
    }

    // if used largest x splitters what gain we will get
    static long getGain(long x, long k) {   
        // gain = totalGain - smallestGain
        long totGain = (k-1) * (k) / 2;
        long rem = k - 1 -x; // remaining gain after x splitters used
        long smallestGain = rem * (rem + 1) / 2;

        return totGain - smallestGain;
    }
}

/*
 * -------------------------------------------------------------------------------------
 * PROBLEM: PIPELINE (Codeforces 1243B)
 * -------------------------------------------------------------------------------------
 * INTUITION:
 * 1. We start with 1 pipe. We need to reach exactly 'n' pipes.
 * 2. Connecting a splitter with 'k' outputs gives a NET GAIN of (k-1) pipes.
 * (Because we use 1 pipe to connect it, and get k new ones).
 * 3. We have exactly one of each splitter size from 2 to k.
 * This means our available GAINS are: 1, 2, 3, ..., k-1.
 * 4. To minimize the number of splitters used, we should always greedily pick
 * the LARGEST available splitters first (k, k-1, k-2...).
 *
 * WHY NOT SIMULATION?
 * - n can be 10^18. Simulating "add largest, then next largest..." is too slow (TLE).
 * - However, the function is MONOTONIC: If 'x' splitters are enough, 'x+1' are also enough.
 * - This allows us to use BINARY SEARCH ON ANSWER.
 *
 * -------------------------------------------------------------------------------------
 * THE MATH: SUM OF LARGEST 'X' NUMBERS
 * -------------------------------------------------------------------------------------
 * We need to calculate the sum of the largest 'x' gains efficiently in O(1).
 * Sequence of gains: 1, 2, 3, ..., k-1.
 *
 * Instead of summing the tail (difficult), we subtract the head (easy).
 *
 * Formula:
 * Sum(Largest x) = Total_Sum_All - Sum(Smallest_Remaining)
 *
 * 1. Total_Sum_All: Sum of 1 to (k-1)
 * = (k-1) * k / 2
 *
 * 2. Remaining_Count: If we pick 'x' items, we leave behind ((k-1) - x) items.
 * These are the smallest items in the list (1, 2, ... remaining).
 *
 * 3. Sum(Smallest_Remaining): Sum of 1 to Remaining_Count
 * = rem * (rem + 1) / 2
 *
 * 4. Gain = Total_Sum_All - Sum(Smallest_Remaining)
 *
 * -------------------------------------------------------------------------------------
 * ALGORITHM:
 * 1. Edge Case: If n=1, output 0 (already done).
 * 2. Impossible Case: Even if we use ALL splitters, is gain < (n-1)?
 * If yes, output -1.
 * 3. Binary Search:
 * - Range: [1, k-1]
 * - Mid = number of splitters to use.
 * - Check: If getGain(mid) >= (n-1), record answer and try smaller (h = mid - 1).
 * - Else: Need more splitters (l = mid + 1).
 * -------------------------------------------------------------------------------------
 */