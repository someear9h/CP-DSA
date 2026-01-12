import java.io.*;
import java.util.*;

public class Hamburgers {

    static long nb, ns, nc; 
    static long pb, ps, pc; 
    static long ss, sb, sc;
    static long r; 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try (PrintWriter out = new PrintWriter(System.out)) {
            String s = br.readLine();
            for(char ch : s.toCharArray()) {
                if(ch == 'S') ss++;
                if(ch == 'B') sb++;
                if(ch == 'C') sc++;
            }

            var st = new StringTokenizer(br.readLine());

            nb = Integer.parseInt(st.nextToken());
            ns = Integer.parseInt(st.nextToken());
            nc = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            pb = Integer.parseInt(st.nextToken());
            ps = Integer.parseInt(st.nextToken());
            pc = Integer.parseInt(st.nextToken());

            r = Long.parseLong(br.readLine());

            // binary search
            long l = 0, h = 2_000_000_000_000L;
            long ans = 0;
            while(l <= h) {
                long mid = l + (h-l) /2;
                if(canMake(mid)) {
                    // if we can make 'mid' burgers with money 'r'
                    // then lets see if we can make some more
                    ans = mid;
                    l = mid+1;
                } else {
                    h = mid-1;
                }
            }

            out.println(ans);
        }
    }

    // parameter as x -> do we have money to make x burgers
    static boolean canMake(long x) {
        long needB = x * sb;
        long needC = x * sc;
        long needS = x * ss;

        // if we dont have sufficient in the kitchn then 
        // we go to market here
        // cnt = says how much "number" do we need to buy 
        long cntB = Math.max(0, needB - nb);
        long cntC = Math.max(0, needC - nc);
        long cntS = Math.max(0, needS - ns);

        // cntB * pb -> if we have to buy 2 things and each have price 2 then totalSpent 
        // for that product = 4 (2 * 2)
        long totalCost = (cntB * pb) + (cntC * pc) + (cntS * ps);

        return totalCost <= r;
    }
}

/*
 * -------------------------------------------------------------------------------------
 * PROBLEM: HAMBURGERS (Codeforces 371C)
 * -------------------------------------------------------------------------------------
 * INTUITION:
 * 1. The problem asks for the MAXIMUM number of hamburgers we can make.
 * 2. Simulating the process (baking 1 by 1) is too slow because the answer can be huge (10^12).
 * 3. We need a way to check if a specific number 'x' is possible efficiently.
 * 4. This problem is MONOTONIC:
 * - If we can make 100 burgers, we can definitely make 99.
 * - If we CANNOT make 100 burgers, we definitely cannot make 101.
 * - This property allows us to use BINARY SEARCH ON ANSWER.
 *
 * -------------------------------------------------------------------------------------
 * THE CHECK FUNCTION: canMake(x)
 * -------------------------------------------------------------------------------------
 * To verify if we can make 'x' hamburgers:
 * 1. Calculate TOTAL items needed for 'x' burgers:
 * - NeedB = x * recipeB
 * - NeedS = x * recipeS
 * - NeedC = x * recipeC
 *
 * 2. Calculate what we must BUY from the shop:
 * - BuyB = Math.max(0, NeedB - HaveB)
 * - BuyS = Math.max(0, NeedS - HaveS)
 * - ... (Same for Cheese)
 * - Note: Math.max(0, ...) is crucial to avoid negative buying if we have excess stock.
 *
 * 3. Calculate TOTAL COST:
 * - Cost = (BuyB * PriceB) + (BuyS * PriceS) + (BuyC * PriceC)
 *
 * 4. Verdict: Return (Cost <= Rubles_We_Have)
 *
 * -------------------------------------------------------------------------------------
 * ALGORITHM:
 * 1. Parse the recipe string to count B, S, C requirements per burger.
 * 2. Set Binary Search Range:
 * - Low = 0
 * - High = 2 * 10^12 (Safe upper bound covering Money + Initial Inventory).
 * 3. Binary Search:
 * - Mid = (Low + High) / 2
 * - If canMake(Mid) is TRUE:
 * Ans = Mid (This is possible, try for more!)
 * Low = Mid + 1
 * - Else:
 * High = Mid - 1 (Too expensive, reduce count)
 * -------------------------------------------------------------------------------------
 */