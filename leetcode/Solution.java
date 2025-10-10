import java.util.*;

class Solution {
    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
        int n = V;
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            int u = e[0], v = e[1];
            adj.get(u).add(v);
        }

        int[] indegree = new int[n];
        int[] topo = new int[n];
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            for(int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }

        int i = 0;
        while(!q.isEmpty()) {
            int node = q.remove();
            topo[i++] = node;

            for(int adjNode : adj.get(node)) {
                indegree[adjNode]--;
                if(indegree[adjNode] == 0) {
                    q.add(adjNode);
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for(int t : topo) {
            ans.add(t);
        }

        return ans;
    }

    public static void main(String[] args) {
        int V = 4;
        int[][] edges = {{3, 0}, {1, 0}, {2, 0}};

        Solution sol = new Solution();

        ArrayList<Integer> printList = topoSort(V, edges);
        for(int i : printList) {
            System.out.print(i + " ");
        }
    }
}