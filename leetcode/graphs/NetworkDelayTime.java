import java.util.*;

class Pair {
    int node;
    int time;
    
    Pair(int node, int time) {
        this.node = node;
        this.time = time;
    }
}

public class NetworkDelayTime {
    public static int networkDelayTime(int[][] times, int n, int k) {
        // Step 1: Build adjacency list
        List<List<Pair>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] t : times) {
            int u = t[0], v = t[1], w = t[2];
            adj.get(u).add(new Pair(v, w));
        }

        // Step 2: Distance array
        int[] dist = new int[n + 1];
        Arrays.fill(dist, (int)1e9);
        dist[k] = 0;

        // Step 3: Min-heap based on time
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        pq.add(new Pair(k, 0));

        // Step 4: Dijkstra's Algorithm
        while(!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.node;
            int time = curr.time;

            for(Pair neighbor : adj.get(node)) {
                int adjNode = neighbor.node;
                int edgeW = neighbor.time;

                if(time + edgeW < dist[adjNode]) {
                    dist[adjNode] = time + edgeW;
                    pq.add(new Pair(adjNode, dist[adjNode]));
                }
            }
        }

        // Step 5: Find max time to reach any node
        int ans = 0;
        for(int i = 1; i <= n; i++) {
            if(dist[i] == (int)1e9) return -1;
            ans = Math.max(ans, dist[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        

        // Example 1
        int[][] times1 = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n1 = 4, k1 = 2;
        System.out.println("Example 1 Output: " + networkDelayTime(times1, n1, k1));  // Output: 2

        // Example 2
        int[][] times2 = {{1, 2, 1}};
        int n2 = 2, k2 = 1;
        System.out.println("Example 2 Output: " +networkDelayTime(times2, n2, k2));  // Output: 1

        // Example 3
        int[][] times3 = {{1, 2, 1}};
        int n3 = 2, k3 = 2;
        System.out.println("Example 3 Output: " + networkDelayTime(times3, n3, k3));  // Output: -1
    }
}
