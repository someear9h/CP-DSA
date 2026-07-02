import java.util.*;

/**
 * if the numbers are far from max k distance to their original sorted index
 * then the smallest element in array must be first k+1 elements in array
 * 
 * we can use min heap for this and when we store more that k+1 elements we 
 * add smallest (pq.peek()) element to the result array
 */

/**
 * let N = number of elements in array and K = number of elements in pq
 * tc: O(N log K) -> O(N) outer for loop and O(log K) inside outer loop offer() and pop() takes
 * O(log K) because we at most store K+2 elements in pq
 * 
 * sc: O(N + k) -> O(N) for the answer array and O(K) we never store more than K+2 elements in pq
 */
public class SortKsortedarray {
    public static int[] solve(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = arr.length;
        int[] ans = new int[n];
        int j = 0;
        for(int i = 0; i < n; i++) {
            pq.offer(arr[i]);

            if(pq.size() > k+1) {
                ans[j] = pq.poll();
                j++;
            }
        }

        for(int i = j; i < n; i++) {
            ans[i] = pq.poll();
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {6, 5, 3, 2, 8, 10, 9};
        int k = 3;
        int[] ans = solve(arr, k);

        for(int a : ans) System.out.print(a + " ");
        System.out.println();
    }
}
