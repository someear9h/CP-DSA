import java.io.*;
import java.util.*;

class Pair {
    int first;
    int second;
    
    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Tuple {
    int first;
    int second;
    int third;

    Tuple(int f, int s, int t) {
        this.first = f;
        this.second = s;
        this.third = t;
    }
}

public class CheapestFlightsWithinKStops {
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        
        // build an adj list
        List<List<Pair>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // fill edges into adj list
        for(int[] flight : flights) {
            int u = flight[0], v = flight[1], cost = flight[2];
            adj.get(u).add(new Pair(v, cost));
        }

        Queue<Tuple> q = new LinkedList<>(); // stop, node, cost
        q.add(new Tuple(0, src, 0));

        int[] dist = new int[n];
        for(int i = 0; i < n; i++) {
            dist[i] = (int)1e9;
        }

        while(!q.isEmpty()) {
            Tuple curr = q.remove();
            int stops = curr.first;
            int node = curr.second;
            int cost = curr.third; // what ever the cost already is computed

            if(stops > k) continue;
            
            // go through the adjacent node of the node
            for(Pair p : adj.get(node)) {
                int adjNode = p.first;
                int edgeW = p.second;

                if(cost + edgeW < dist[adjNode] && stops <= k) {
                    dist[adjNode] = cost + edgeW;
                    q.add(new Tuple(stops + 1, adjNode, cost + edgeW));
                } 
            }
        }

        if(dist[dst] == (int)1e9) return -1;
        return dist[dst];
    }

    public static void main(String[] args) throws IOException {
        int n = 3;
        int src = 0;
        int dst = 2;
        int k = 1;
        int[][] flights = {{0,1,100}, {1,2,100}, {0,2,500}};

        System.out.println(findCheapestPrice(n, flights, src, dst, k));
    }
}