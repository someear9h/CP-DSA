import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlienDictionary {
    public static String findOrder(String[] dict, int K) {
        int V = K;  // Number of distinct characters in the alien language
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Build the graph
        for (int i = 0; i < dict.length - 1; i++) {
            String word1 = dict[i];
            String word2 = dict[i + 1];
            int len = Math.min(word1.length(), word2.length());

            for (int j = 0; j < len; j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    int u = word1.charAt(j) - 'a';
                    int v = word2.charAt(j) - 'a';
                    adj.get(u).add(v);
                    break;  // Only first difference matters
                }
            }
        }

        List<Integer> topo = topoSort(V, adj);

        // If there's a cycle or not all nodes are in topo, return empty string
        if (topo.size() < K) return "";

        StringBuilder sb = new StringBuilder();
        for (int ch : topo) {
            sb.append((char) (ch + 'a'));
        }

        return sb.toString();
    }

    private static List<Integer> topoSort(int V, List<List<Integer>> adj) {
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int node : adj.get(i)) {
                indegree[node]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int curr = q.poll();
            topo.add(curr);
            for (int next : adj.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0)
                    q.add(next);
            }
        }

        return topo;
    }

    // You can test it with the main method below
    public static void main(String[] arg) {

        // Example 1
        String[] dict1 = {"baa", "abcd", "abca", "cab", "cad"};
        int K1 = 4;
        System.out.println("Order: " + findOrder(dict1, K1)); // Output: bdac (or any valid)

        // Example 2
        String[] dict2 = {"caa", "aaa", "aab"};
        int K2 = 3;
        System.out.println("Order: " + findOrder(dict2, K2)); // Output: cab (or any valid)
    }
}
