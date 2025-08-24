public class Checkifanarrayrepresentsaminheap {
    private static boolean isMinHeap(int[] nums) {
        int n = nums.length;
        
        // till index (n / 2) -1 are the root nodes 
        for(int i = 0; i < (n / 2) - 1; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if(left < n && nums[i] > nums[left]) {
                return false;
            }

            if(right < n && nums[i] > nums[right]) {
                return false;
            }
        }

        return true;
    }    

    public static void main(String[] args) {
        int[] nums = {10, 20, 30, 21, 23};

        System.out.println(isMinHeap(nums));
    }
}

/**
Approach

Loop from index 0 to (n/2 - 1) (because nodes beyond that are leaves, no children).

For each i:

Compute left = 2*i + 1, right = 2*i + 2

If left exists and nums[i] > nums[left], return false

If right exists and nums[i] > nums[right], return false

After loop → return true.
*/