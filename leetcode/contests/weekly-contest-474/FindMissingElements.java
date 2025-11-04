import java.util.*;

public class FindMissingElements {
    public static List<Integer> findMissingElements(int[] nums) {
        int mx = Integer.MIN_VALUE, mn = Integer.MAX_VALUE;
        int n = nums.length;

        for(int num : nums) {
            mx = Math.max(mx, num);
            mn = Math.min(mn, num);
        }

        boolean[] isPresent = new boolean[101];

        for(int i = 0; i < n; i++) {
            isPresent[nums[i]] = true;
        }

        List<Integer> res = new ArrayList<>();

        for(int i = mn + 1; i < mx; i++) {
            if(!isPresent[i]) {
                res.add(i);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,4,2,5};
        List<Integer> res = findMissingElements(nums);

        for(int i : res) System.out.print(i + " ");
    }
}
