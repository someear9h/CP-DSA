
/**
 * someear1h
*/

import java.util.*;
import java.io.*;

public class E {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        try (var out = new PrintWriter(System.out)) {
            int t = Integer.parseInt(br.readLine().trim());

            while (t-- > 0) {
                int n = Integer.parseInt(br.readLine().trim());
                int[] a = new int[n];
                var st = new StringTokenizer(br.readLine().trim());
                for (int i = 0; i < n; i++) {
                    a[i] = Integer.parseInt(st.nextToken());
                }

                boolean sortedA = true;
                for (int i = 0; i < n; i++) {
                    if (i > 0 && a[i] < a[i - 1])
                        sortedA = false;
                }

                if (sortedA) {
                    out.println("Bob");
                    continue;
                }

                boolean sortedB = true;
                boolean isPoison = false;

                int[] b = new int[n];
                for (int i = 0; i < n; i++) {
                    b[i] = primeBase(a[i]);
                    if (b[i] == -1)
                        isPoison = true;
                    if (i > 0 && b[i] < b[i - 1])
                        sortedB = false;
                }

                if (isPoison) {
                    out.println("Alice");
                } else if (sortedB) {
                    out.println("Bob");
                } else
                    out.println("Alice");
            }
        }
    }

    static int primeBase(int x) {
        int distinctPrimes = 0;
        int lastPrime = 1;

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                distinctPrimes++;
                lastPrime = i;
                while (x % i == 0) {
                    x /= i;
                }
            }
        }

        if (x > 1) {
            distinctPrimes++;
            lastPrime = x;
        }

        if (distinctPrimes > 1)
            return -1;
        if (distinctPrimes == 0)
            return 1;
        return lastPrime;
    }
}