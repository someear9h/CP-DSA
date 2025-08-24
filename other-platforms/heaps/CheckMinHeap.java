public class CheckMinHeap {

    /**
     * Checks if a given array represents a valid min-heap.
     *
     * Intuition:
     * A min-heap has a specific property: for any node at index `i`, its value
     * must be less than or equal to the values of its children.
     * The children of a node at index `i` are at indices `2*i + 1` (left) and `2*i + 2` (right).
     * We only need to check this property for non-leaf nodes, as leaf nodes have no children.
     * The last non-leaf node in a heap of size `n` is at index `(n/2) - 1`.
     *
     * @param arr The array to check.
     * @return true if the array is a min-heap, false otherwise.
     */
    public static boolean isMinHeap(int[] arr) {
        int n = arr.length;

        // We only need to check the parent nodes.
        // The last parent is at index (n/2) - 1. We loop from there up to the root (index 0).
        for (int i = (n / 2) - 1; i >= 0; i--) {
            // Calculate indices of left and right children.
            int leftChildIdx = 2 * i + 1;
            int rightChildIdx = 2 * i + 2;

            // Check the min-heap property for the left child.
            // If the parent is greater than its left child, it's not a min-heap.
            if (arr[i] > arr[leftChildIdx]) {
                return false;
            }

            // Check the min-heap property for the right child, if it exists.
            if (rightChildIdx < n && arr[i] > arr[rightChildIdx]) {
                return false;
            }
        }

        // If we get through the whole loop without finding any violations, it's a valid min-heap.
        return true;
    }

    public static void main(String[] args) {
        int[] validMinHeap = {10, 20, 30, 40, 50};
        int[] invalidMinHeap = {90, 15, 10, 7, 12, 2};

        System.out.println("Is {10, 20, 30, 40, 50} a min-heap? " + isMinHeap(validMinHeap)); // Expected: true
        System.out.println("Is {90, 15, 10, 7, 12, 2} a min-heap? " + isMinHeap(invalidMinHeap)); // Expected: false
    }
}