/**
someear1h 
*/
 
import java.util.*;
public class Solution {
    static int[][] c_n_k = new int[31][31];
    public static void main(String[] args) {
        for (int i = 0; i < 30; ++i) {
            for (int j = 0; j < 30; ++j) {
                if (i < j) c_n_k[i][j] = 0;
                else if (j == 0) c_n_k[i][j] = 1;
                else c_n_k[i][j] = c_n_k[i - 1][j] + c_n_k[i - 1][j - 1];
            }
        }

        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();

            int d = 0;
            while (n % 2 == 0) {
                n /= 2;
                ++d;
            }

            int ans = 0;
            for (int max_bit = 0; max_bit < d; ++max_bit) {
                for (int cnt_bit = 1; cnt_bit <= max_bit + 1; ++cnt_bit) {
                    if (max_bit + cnt_bit <= k) continue;
                    ans += c_n_k[max_bit][cnt_bit - 1];
                }
            }

            if (d + 1 > k) ++ans;
            System.out.println(ans);
        }

        scanner.close();
    }
}
