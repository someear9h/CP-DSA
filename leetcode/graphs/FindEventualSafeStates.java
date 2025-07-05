import java.util.*;

public class FindEventualSafeStates {
    private static List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        boolean[] vis = new boolean[n];
        boolean[] visPath = new boolean[n];
        boolean[] safe = new boolean[n];
        List<Integer> ans = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(dfsCycleCheck(i, vis, visPath, graph, safe) == false) {
                ans.add(i);
            }    
        }

        return ans;
    }

    private static boolean dfsCycleCheck(int node, boolean[] vis, boolean[] visPath, int[][] adj, boolean[] safe) {

        if(vis[node]) return !safe[node];

        vis[node] = true;
        visPath[node] = true;

        for(int it : adj[node]) {
            if(!vis[it]) {
                if(dfsCycleCheck(it, vis, visPath, adj, safe)) {
                    return true;
                }
            } else if(visPath[it]) {
                return true;
            }
        }
        visPath[node] = false;
        safe[node] = true;
        return false;
    }

    public static void main(String[] args) {
        int[][] graph = {
            {1, 2},
            {2, 3},
            {5},
            {0},
            {5},
            {},
            {}
        };

        List<Integer> result = eventualSafeNodes(graph);

        System.out.println("Safe Nodes: " + result);
    }
}
