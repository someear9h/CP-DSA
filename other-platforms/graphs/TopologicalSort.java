import java.io.*;
import java.util.*;

public class TopologicalSort {
    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        int[] vis = new int[V];
        
        // initialize the adj list for every vertex
        for(int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int[] e : edges) {
            int u = e[0], v = e[1];
            adj.get(u).add(v);
        }
        
        // go through every node now
        for(int i = 0;i < V; i++) {
            if(vis[i] == 0) {
                dfs(i, vis, st, adj);
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        while(!st.isEmpty()) {
            int num = st.peek();
            ans.add(num);
            st.pop();
        }
        
        return ans;
    }
    
    private static void dfs(int node, int[] vis, Stack<Integer> st, 
        ArrayList<ArrayList<Integer>> adj) {
        
        vis[node] = 1;
        for(int it : adj.get(node)) {
            if(vis[it] == 0) dfs(it, vis, st, adj);
        }
        st.push(node);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int t = Integer.parseInt(br.readLine()); // number of test cases

        while(t-- > 0) {
            // Read V and E
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            // Read edges
            int[][] edges = new int[E][2];

            for(int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
            }

            ArrayList<Integer> ans = topoSort(V, edges);
            for(int val : ans) {
                out.print(val + " ");
            }
            out.println();
        }
        out.flush();
    }
}
