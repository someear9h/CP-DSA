import java.util.Arrays;

public class ConvertMinHeaptoMaxHeap {
    public static int[] convertToMaxHeap(int[] nums) {
        int n = nums.length;

        for(int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }
        
        return nums;
    }    

    private static void heapify(int[] nums, int n, int i) {
        int root = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < n && nums[left] > nums[root]) {
            root = left;
        }

        if(right < n && nums[right] > nums[root]) {
            root = right;
        }

        if(root != i) {
            int temp = nums[i];
            nums[i] = nums[root];
            nums[root] = temp;

            heapify(nums, n, root);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {10, 20, 30, 21, 23};
        int[] nums2 = {-5, -4, -3, -2, -1};
        int[] nums3 = {2, 6, 3, 100, 120, 4, 5};

        System.out.println(Arrays.toString(convertToMaxHeap(nums1)));
        System.out.println(Arrays.toString(convertToMaxHeap(nums2)));
        System.out.println(Arrays.toString(convertToMaxHeap(nums3)));

    }
}
