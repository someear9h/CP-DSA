import java.util.ArrayList;
import java.util.List;

public class Subsets {
    List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        
        backtrack(nums, 0, res, subset);
        return res;
    }

    void backtrack(int[] nums, int idx, List<List<Integer>> res, List<Integer> subset) {
        // base case
        if(idx == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }

        // pick the current element and move ahead
        subset.add(nums[idx]);
        backtrack(nums, idx + 1, res, subset);

        // dont pick the element and move ahead
        subset.remove(subset.size() - 1);
        backtrack(nums, idx + 1, res, subset);
    }
}