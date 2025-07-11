import java.util.*;

class Pair {
    long dist;
    int node;

    Pair(long dist, int node) {
        this.dist = dist;
        this.node = node;
    }
}

public class NumberofWaystoArriveatDestination {
    public static int countPaths(int n, int[][] roads) {
        // make an adj list
        List<List<Pair>> adj = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] r : roads) {
            int u = r[0], v = r[1], wt = r[2];
            adj.get(u).add(new Pair(wt, v));
            adj.get(v).add(new Pair(wt, u));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
        long[] dist = new long[n];
        int[] ways = new int[n];

        for(int i = 0; i < n; i++) {
            dist[i] = Long.MAX_VALUE;
            ways[i] = 0;
        }

        pq.add(new Pair(0L, 0)); // dist, node
        dist[0] = 0L;
        ways[0] = 1;

        int mod = (int)(1e9 + 7);

        while(!pq.isEmpty()) {
            long d = pq.peek().dist;
            int currNode = pq.peek().node;
            pq.remove();

            // Skip if we already found a better distance
            if (d > dist[currNode]) {
                continue;
            }

            // go through adjacent nodes
            for(Pair p : adj.get(currNode)) {
                int adjNode = p.node;
                long edW = p.dist;

                if(d + edW < dist[adjNode]) {
                    dist[adjNode] = d + edW;
                    pq.add(new Pair(dist[adjNode], adjNode));
                    ways[adjNode] = ways[currNode];
                } else if(d + edW == dist[adjNode]) {
                    ways[adjNode] = (ways[adjNode] + ways[currNode]) % mod;
                }
            }
        }

        return ways[n - 1] % mod;
    }

    public static void main(String[] args) {

        
        int n = 6;
        int[][] roads = {
            {0, 1, 1000000000},
            {0, 3, 1000000000},
            {1, 3, 1000000000},
            {1, 2, 1000000000},
            {1, 5, 1000000000},
            {3, 4, 1000000000},
            {4, 5, 1000000000},
            {2, 5, 1000000000}
        };

        int result = countPaths(n, roads);
        System.out.println("Number of shortest paths: " + result); // Expected: 3
    }
}
