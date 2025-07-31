import java.util.*;

public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();

        recur(0, candidates, target, res, comb);
        return res;
    }

    private static void recur(int idx, int[] candidates, int target, List<List<Integer>> res, List<Integer> comb) {
        
        // base case
        if(idx >= candidates.length) {
            if(target == 0) {
                res.add(new ArrayList<>(comb));
            }

            return;
        }

        // picking the current element
        if(candidates[idx] <= target) {
            comb.add(candidates[idx]); // add the current element
            recur(idx, candidates, target - candidates[idx], res, comb);
            comb.remove(comb.size() - 1);
        }

        // not picking current element
        recur(idx + 1, candidates, target, res, comb);
    }
}
