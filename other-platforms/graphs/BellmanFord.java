import java.util.*;

public class BellmanFord {
    public static int[] bellmanFord(int V, List<List<Integer>> edges, int S) {
        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e8);
        dist[S] = 0;

        // go through every egde in graph
        for(int i = 0; i < V-1; i++) {

            for(List<Integer> e : edges) {
                int u = e.get(0);
                int v = e.get(1);
                int w = e.get(2);

                // if u is reachable and has smaller distance
                if(dist[u] != (int)1e8 && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // we loop again to see if there are any cycles
        // a non cyclic path has V-1 edges for which we have calculated the min distance
        // but if we still go through edges and find lower distance that means we have a negative cycle
        // like we have gone to the Vth edge now which is impossbile for the non cyclic path
        for(List<Integer> e : edges) {
            int u = e.get(0);
            int v = e.get(1);
            int w = e.get(2);

            if(dist[u] != (int)1e8 && dist[u] + w < dist[v]) {
                int[] temp = new int[1];
                temp[0] = -1;
                return temp;
            }
        }

        return dist;
    } 
    
    public static void main(String[] args) {
        int V = 6;
        int S = 0;
        List<List<Integer>> edges = new ArrayList<>() {
            {
                add(new ArrayList<Integer>(Arrays.asList(3, 2, 6)));
                add(new ArrayList<Integer>(Arrays.asList(5, 3, 1)));
                add(new ArrayList<Integer>(Arrays.asList(0, 1, 5)));
                add(new ArrayList<Integer>(Arrays.asList(1, 5, -3)));
                add(new ArrayList<Integer>(Arrays.asList(1, 2, -2)));
                add(new ArrayList<Integer>(Arrays.asList(3, 4, -2)));
                add(new ArrayList<Integer>(Arrays.asList(2, 4, 3)));
            }
        };
        int[] dist = bellmanFord(V, edges, S);
        for (int i = 0; i < V; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println("");
    }
}