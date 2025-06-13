import java.util.*;

public class MaximumBagsWithFullCapacityofRocks {
    public static int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = rocks.length, maxBags = 0;

        for(int i = 0; i < n; i++) {
            rocks[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(rocks);

        for(int i = 0; i < n && additionalRocks >= rocks[i]; i++) {
            maxBags++;
            additionalRocks -= rocks[i];
        }
        
        return maxBags;
    }

    public static void main(String[] args) {
        int[] capacity = {2,3,4,5};
        int[] rocks = {1,2,4,4};
        int additionalRocks = 2;

        System.out.println(maximumBags(capacity, rocks, additionalRocks));
    }
}