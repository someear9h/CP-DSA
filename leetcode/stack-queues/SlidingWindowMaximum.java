import java.util.*;

public class SlidingWindowMaximum {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1]; // number of windows

        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            while(!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.removeFirst();
            }

            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.removeLast();
            }

            dq.addLast(i);

            if(i >= k - 1) {
                res[i - k + 1] = nums[dq.peekFirst()];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        int[] res = maxSlidingWindow(nums, k);
        for(int r : res) {
            System.out.print(r + " ");
        }
    }
}
