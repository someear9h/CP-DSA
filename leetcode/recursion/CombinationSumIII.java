import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subs = new ArrayList<>();;

        helper(1, k, n, res, subs);
        return res;
    }

    private void helper(int idx, int k, int n, List<List<Integer>> res, List<Integer> subs) {
        if(k == 0 && n == 0) {
            res.add(new ArrayList<>(subs));
            return;
        }

        if(k < 0 || n < 0) return;

        for(int i = idx; i <= 9; i++) {
            subs.add(i); // pick current element and move ahead
            helper(i + 1, k - 1, n - i, res, subs);

            // backtrack
            subs.remove(subs.size() - 1);
        }
    }
}
