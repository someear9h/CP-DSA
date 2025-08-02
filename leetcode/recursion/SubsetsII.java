import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        /**
            -> i will sort the nums array
            -> lets do pick and non pick here
        */  

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subs = new ArrayList<>();

        helper(0, nums, res, subs);

        return res;
    }

    private void helper(int idx, int[] nums, List<List<Integer>> res, List<Integer> subs) {
        // base case
        if(idx == nums.length) {
            res.add(new ArrayList<>(subs));
            return;
        }

        // pick current element
        subs.add(nums[idx]);
        helper(idx + 1, nums, res, subs);
        subs.remove(subs.size() - 1);

        // dont pick current element
        while(idx + 1 < nums.length && nums[idx] == nums[idx + 1]) {
            idx++;
        }
        helper(idx + 1, nums, res, subs);
    }
}
