import java.util.Collections;
import java.util.PriorityQueue;

public class RemoveStonestoMinimizetheTotal {
    public static int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int p : piles) pq.add(p);

        // get the top k largest element from the pq
        while(k > 0) {
            // get the largst element currently in piles
            int largest = pq.remove(); 
            int floor = largest - (largest / 2); // eg 9 - (9 / 2) = 5
            
            // add the floored element again the piles for further operations
            pq.add(floor); 
            
            k--;
        }

        long sum = 0;
        while(!pq.isEmpty()) {
            sum += pq.remove();
        }

        return (int) sum;
    }  

    public static void main(String[] args) {
        int[] piles = {5, 4, 9};
        int k = 2;

        System.out.println(minStoneSum(piles, k));
    }
}
