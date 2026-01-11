import java.io.*;
import java.util.*;

public class MagicPowder1 {
    static int n;
    static int k;
    static int[] cost, tobuy;
    static int[] a;
    static int[] b;

    static boolean canMake(int x) {
        // to bake x cookies
        cost = new int[n];
        tobuy = new int[n];
        for(int i= 0; i < n; i++) {
            cost[i] = x * a[i];
        }

        for(int i = 0; i < n; i++) {
            tobuy[i] = Math.max(0, cost[i] - b[i]);
        }

        int tpn = 0;
        for(int i = 0; i < n; i++) {
            tpn += tobuy[i];
        }

        return tpn <= k;
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        a= new int[n]; b = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        // binary search thing here
        int l = 0, h = 10_000;
        int ans = 0;
        while(l <= h) {
            int mid = l + (h - l) / 2;
            if(canMake(mid)) {
                ans = mid;
                l = mid+1;
            } else {
                h = mid -1;
            }
        }

        System.out.println(ans);
    }
}

/*
 * -------------------------------------------------------------------------------------
 * PROBLEM: MAGIC POWDER - 1 (Codeforces 670D1)
 * -------------------------------------------------------------------------------------
 * INTUITION:
 * 1. We want to find the MAXIMUM number of cookies 'x' we can bake.
 * 2. We have 'n' different ingredients. For each ingredient 'i', we know:
 * - a[i]: grams needed per cookie
 * - b[i]: grams we already have
 * 3. We have 'k' grams of magic powder, which acts as a "wildcard" filler for ANY ingredient.
 * 4. Instead of simulating baking cookies one by one (which works for D1 but fails D2),
 * we use BINARY SEARCH ON ANSWER for an optimal O(N log Ans) solution.
 *
 * -------------------------------------------------------------------------------------
 * THE CHECK FUNCTION: canMake(x)
 * -------------------------------------------------------------------------------------
 * To verify if we can bake 'x' cookies:
 * 1. Initialize 'magicPowderNeeded' = 0.
 * 2. Iterate through all 'n' ingredients:
 * - Total_Need_For_Ingredient = x * a[i]
 * - Have = b[i]
 * - Deficit = Total_Need - Have
 *
 * 3. If (Deficit > 0), we must use magic powder to cover it:
 * - magicPowderNeeded += Deficit
 *
 * 4. Verdict:
 * - If at any point (magicPowderNeeded > k), return FALSE (Optimization).
 * - Otherwise, check if final (magicPowderNeeded <= k).
 *
 * -------------------------------------------------------------------------------------
 * ALGORITHM:
 * 1. Read 'n' and 'k'. Read arrays 'a' (costs) and 'b' (inventory).
 * 2. Binary Search:
 * - Range: [0, 2_000_000_000] (Safe bound).
 * - Note for D1: Max answer is small (around 2000), so range 0-10000 works.
 * - Note for D2: Must use range up to 2*10^9 and 'long' variables.
 * 3. Loop:
 * - Mid = (Low + High) / 2
 * - If canMake(Mid):
 * Ans = Mid
 * Low = Mid + 1
 * - Else:
 * High = Mid - 1
 * -------------------------------------------------------------------------------------
 */