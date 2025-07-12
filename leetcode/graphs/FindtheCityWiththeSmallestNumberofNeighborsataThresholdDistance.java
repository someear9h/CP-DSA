import java.util.*;

class Pair {
    int first;  // distance
    int second; // node

    Pair(int f, int s) {
        this.first = f;
        this.second = s;
    }
}

public class FindtheCityWiththeSmallestNumberofNeighborsataThresholdDistance {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // build an adj list
        List<List<Pair>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            int u = e[0], v = e[1], wt = e[2];
            adj.get(u).add(new Pair(v, wt));
            adj.get(v).add(new Pair(u, wt));
        }

        int minReachable = Integer.MAX_VALUE;
        int cityNo = -1;
        
        // Dijkstra algorithm for every node separately
        for (int src = 0; src < n; src++) {
            // Dijkstra from each node
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.first, b.first));
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;
            pq.add(new Pair(0, src));

            while(!pq.isEmpty()) {
                int distance = pq.peek().first;
                int node = pq.peek().second;
                pq.poll();

                for(Pair p : adj.get(node)) {
                    int adjNode = p.first;
                    int edW = p.second;

                    if (distance + edW < dist[adjNode]) {
                        dist[adjNode] = distance + edW;
                        pq.add(new Pair(dist[adjNode], adjNode));
                    }
                }
            }

            // Count reachable cities within threshold
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (i != src && dist[i] <= distanceThreshold) {
                    count++;
                }
            }

            // Select city with min reachable or higher city number in case of tie
            if (count <= minReachable) {
                minReachable = count;
                cityNo = src;
            }
        }

        return cityNo;
    }
}