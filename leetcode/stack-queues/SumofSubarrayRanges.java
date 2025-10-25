import java.util.Stack;

public class SumofSubarrayRanges {
    public long subArrayRanges(int[] nums) {
        int n = nums.length;

        return getMaxContri(nums, n) - getMiniContri(nums, n);
    }

    public long getMiniContri(int[] nums, int n) {
        int[] ps = prevSmaller(nums, n);
        int[] ns = nextSmaller(nums, n);

        long sum = 0;

        for(int i = 0; i < n; i++) {
            int left = i - ps[i];
            int right = ns[i] - i;

            long contri = (long)left * right * nums[i];

            sum += contri;
        }

        return sum;
    }

    public long getMaxContri(int[] nums, int n) {
        int[] pg = prevGreater(nums, n);
        int[] ng = nextGreater(nums, n);

        long sum = 0;
        for(int i = 0; i < n; i++) {
            int left = i - pg[i];
            int right = ng[i] - i;

            long contri = (long)left * right * nums[i];
            sum += contri;
        }

        return sum;
    }

    public int[] nextGreater(int[] nums, int n) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[n];

        for(int i = n - 1; i >= 0; i--) {
            while(!st.isEmpty() && nums[st.peek()] <= nums[i]) {
                st.pop();
            }

            res[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        return res;
    }

    public int[] prevGreater(int[] nums, int n) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[n];
        for(int i = 0; i < n; i++) {
            while(!st.isEmpty() && nums[st.peek()] < nums[i]) {
                st.pop();
            }

            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        return res;
    }

    public int[] nextSmaller(int[] nums, int n) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[n];

        for(int i = n - 1; i >= 0; i--) {
            while(!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                st.pop();
            }

            res[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        return res;
    }

    public int[] prevSmaller(int[] nums, int n) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[n];

        for(int i = 0; i < n; i++) {
            while(!st.isEmpty() && nums[st.peek()] > nums[i]) {
                st.pop();
            } 

            res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        SumofSubarrayRanges sol = new SumofSubarrayRanges();

        int[] nums1 = {1, 2, 3};
        int[] nums2 = {1, 3, 3};

        System.out.println(sol.subArrayRanges(nums1));
        System.out.println(sol.subArrayRanges(nums2));
    }
}
