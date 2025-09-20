import java.util.*;

public class Solution {
    private final int MOD = (int)1e9 + 7;
    public int sumSubarrayMins(int[] arr) {
        // we have to get in how many subarrays the arr[i] is minimum
        // we find next smaller element and previous smaller element for arr[i];
        // each arr[i] contributes to ans as arr[i] * left[i] * right[i]

        int n = arr.length;
        int[] left = new int[n]; // left[i] stores how many subarrays ith element is minimum from left
        int[] right = new int[n]; // same but for right
        
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < n; i++) {
            while(!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            // the top of stack has first lesser element of arr[i] from left
            // and the no of subarrays are curr idx and the first lesser element's index
            left[i] = st.isEmpty() ? i + 1 : i - st.peek();
            st.push(i);
        }

        while(!st.isEmpty()) st.pop();

        for(int i = n - 1; i >= 0; i--) {
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            right[i] = st.isEmpty() ? n - i : st.peek() - i;
            st.push(i);
        }

        long ans = 0;
        for(int i = 0; i < n; i++) {
            ans = ans + (long) left[i] * right[i] * arr[i];
        }

        return (int)ans;
    }


}