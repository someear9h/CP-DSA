import java.util.Collections;
import java.util.PriorityQueue;

public class KthLargestElementinanArray {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int num : nums) {
            pq.add(num);
        }

        for(int i = 0; i < k - 1; i++) {
            pq.remove();
        }

        return pq.peek();
    }
}

/**
    we put all elements of array in priority queue in reverse like maxheap
    heap = [6, 5, 4, 3, 2, 1]

    i will pop this heap k - 1 times and then the peek() element is answer

    Let’s analyze time complexity (TC) and space complexity (SC):

1. Inserting all elements into PriorityQueue

A PriorityQueue in Java is a binary heap (logarithmic insert/remove).

Each pq.add(num) takes O(log n).

Doing this for all n elements → O(n log n).

2. Removing (k - 1) elements

Each pq.remove() takes O(log n).

Repeating (k - 1) times → O(k log n).

3. Peeking the top element

pq.peek() is O(1).

✅ Total TC = O(n log n + k log n)
Since k ≤ n, we can say O(n log n) in the worst case.

Space Complexity

The PriorityQueue stores all n elements.

So SC = O(n).
*/