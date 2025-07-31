import java.util.*;

public class CombinationSumII {
    static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();

        Arrays.sort(candidates);
        
        helper(0, candidates, target, res, sub);
        return res;
    }

    static private void helper(int idx, int[] candidates, int target, List<List<Integer>> res, List<Integer> sub) {
        // base case

        if(target == 0) {
            res.add(new ArrayList<>(sub));
            return;
        }

        if(idx == candidates.length || target < 0) {
            return;
        }

        // pick current element
        sub.add(candidates[idx]);
        helper(idx + 1, candidates, target - candidates[idx], res, sub);
        sub.remove(sub.size() - 1);

        // If we decide not to pick the current element, we must also skip all of its
        // subsequent identical elements to avoid creating duplicate combinations.
        while(idx + 1 < candidates.length && candidates[idx] == candidates[idx + 1]) {
            idx++;
        }

        helper(idx + 1, candidates, target, res, sub);
    }
}
