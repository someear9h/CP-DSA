import java.util.*;

public class HeapDemo {
    public static void main(String[] args) {
        System.out.println("--- Min Heap ---");
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.add(10);
        minHeap.add(4);
        minHeap.add(9);
        minHeap.add(23);

        System.out.println("Min Heap: " + minHeap);
        System.out.println("top Element: " + minHeap.peek());

        System.out.println("removing elements from minHeap: ");
        
        while(!minHeap.isEmpty()) {
            System.out.print(minHeap.remove() + " ");
        }

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("--- Max Heap using comparator ---");

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(5);
        maxHeap.add(15);

        System.out.println("Max Heap: " + maxHeap);
        System.out.println("Top element: " + maxHeap.peek());

        System.out.println("Removing elements from maxHeap: ");
        while(!maxHeap.isEmpty()) {
            System.out.print(maxHeap.remove() + " ");
        }
        
        System.out.println();
    }
}
