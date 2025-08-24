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

/**
 * 
 * Problem Restatement

We’re given an array nums that already represents a min-heap.
We want to convert it into a max-heap (array representation) and return the result.

🔹 Intuition

In a min-heap, each parent ≤ children.

In a max-heap, each parent ≥ children.

We can’t just "reverse" the array — the heap property must be preserved.
So the correct approach is:
👉 Re-heapify the array into a max-heap.

🔹 Approach

Heapify Concept:

A heapify(i) function ensures the subtree rooted at index i satisfies the max-heap property:

Compare node i with its left & right children.

Swap with the largest child if needed.

Recursively fix the subtree.

Building a max-heap:

Start from the last non-leaf node (n/2 - 1) down to root (0).

Call heapify(i) on each node.

This ensures all subtrees satisfy max-heap property → the entire array is a valid max-heap.
 */