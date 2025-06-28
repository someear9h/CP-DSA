#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    // Modified dfs function to also count edges
    void dfs(int node, vector<vector<int>>& adj, vector<bool>& vis, vector<int>& comp, int& edgeCount) {
        vis[node] = true;
        comp.push_back(node);
        edgeCount += adj[node].size(); // Count all edges from this node

        for(auto it : adj[node]) {
            if(!vis[it]) {
                dfs(it, adj, vis, comp, edgeCount);
            }
        }
    }

    // No longer need isCompleteComponent since we check mathematically

    // Modified count function
    int countCompleteComponents(int n, vector<vector<int>>& edges) {
        vector<vector<int>> adj(n);
        
        // Build adjacency list only (no need for adjSet)
        for(auto edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].push_back(v);
            adj[v].push_back(u);
        }

        int cnt = 0;
        vector<bool> vis(n, false);

        for(int i = 0; i < n; i++) {
            if(!vis[i]) {
                vector<int> comp;
                int edgeCount = 0;
                dfs(i, adj, vis, comp, edgeCount);
                
                // Check if complete component using mathematical formula
                int m = comp.size();
                if(edgeCount == m * (m - 1)) { // Each edge counted twice
                    cnt++;
                }
                /*
                
Explanation of the Formula: m * (m - 1)
This formula is used to verify if a component in an undirected graph is a complete graph (every pair of nodes is directly connected by an edge).

Key Idea:
In a complete undirected graph with m nodes:

Each node connects to m - 1 other nodes.

Since each edge is counted twice (once in each direction in the adjacency list), the total number of edge entries in the adjacency list should be:
Total edge count=m×(m−1)
                */
            }
        }

        return cnt;
    }
};

int main() {
    Solution sol;

    // Test Case 1: 3 complete components (as in LeetCode Example 1)
    int n1 = 6;
    vector<vector<int>> edges1 = {{0,1}, {0,2}, {1,2}, {3,4}};
    cout << "Test Case 1: " << sol.countCompleteComponents(n1, edges1) << endl;  // Output: 3

    // Test Case 2: 1 complete component (as in LeetCode Example 2)
    int n2 = 6;
    vector<vector<int>> edges2 = {{0,1}, {0,2}, {1,2}, {3,4}, {3,5}};
    cout << "Test Case 2: " << sol.countCompleteComponents(n2, edges2) << endl;  // Output: 1

    // Test Case 3: Single node (trivially complete)
    int n3 = 1;
    vector<vector<int>> edges3 = {};
    cout << "Test Case 3: " << sol.countCompleteComponents(n3, edges3) << endl;  // Output: 1

    return 0;
}