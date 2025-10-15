import java.io.*;
import java.util.*;

public class MaximumNumberofRobotsWithinBudget {
    public static int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length;
        long winSum = 0;
        int left = 0, res = 0;

        // max-heap: stores {chargeTime, index}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        for (int right = 0; right < n; right++) {
            winSum += runningCosts[right];
            pq.offer(new int[]{chargeTimes[right], right});

            // remove elements from heap that are no longer in window
            while (!pq.isEmpty() && pq.peek()[1] < left) pq.poll();

            int maxEle = pq.isEmpty() ? 0 : pq.peek()[0];
            long cost = maxEle + (right - left + 1) * winSum;

            while (cost > budget) {
                winSum -= runningCosts[left];
                left++;

                // remove stale elements again after moving left
                while (!pq.isEmpty() && pq.peek()[1] < left) pq.poll();
                maxEle = pq.isEmpty() ? 0 : pq.peek()[0];
                cost = maxEle + (right - left + 1) * winSum;
            }

            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int budget = Integer.parseInt(st.nextToken());

        int[] nums1 = new int[n];
        int[] nums2 = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            nums1[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            nums2[i] = Integer.parseInt(st.nextToken());
        }

        out.println(maximumRobots(nums1, nums2, budget));
        out.close();
    }
}
