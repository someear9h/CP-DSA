import java.util.*;

public class MaximizeSumofAtMostKDistinctElements {
    public static int[] maxKDistinct(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for(int i : nums) {
            set.add(i);
        }

        int[] arr = new int[set.size()];
        int idx = 0;
        for(int val : set) {
            arr[idx] = val;
            idx++;
        }
        
        Arrays.sort(arr); // ascending
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        int limit = Math.min(k, arr.length);
        int[] res = new int[limit];
        for (int j = 0; j < limit; j++) {
            res[j] = arr[j];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {84,93,100,77,90};
        int k = 3;
        int[] ans = maxKDistinct(nums, k);
        
        for(int i : ans) {
            System.out.print(i);
        }
    }
}
