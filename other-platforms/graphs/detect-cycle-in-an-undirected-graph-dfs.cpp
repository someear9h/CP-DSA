#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Solution {
public:
    bool dfs(int node, int parent, vector<vector<int>>& adj, vector<bool>& vis) {
        vis[node] = true;
        for(auto currNode : adj[node]) {
            if(!vis[currNode]) {
               if(dfs(currNode, node, adj, vis) == true) return true;
            } else if(parent != currNode) {
                return true;
            }
        }
        return false;
    }

    bool isCycle(int V, vector<vector<int>>& edges) {
        // make adjacency list
        vector<vector<int>> adj(V);
        for(auto edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].push_back(v);
            adj[v].push_back(u);
        }

        vector<bool> vis(V);
        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                if(dfs(i, -1, adj, vis) == true) return true; 
            }
        }
        return false;
    }
};

int main() {
    Solution sol;
    int V = 4, E = 4;
    vector<vector<int>> edges = {
        {0, 1}, {0, 2}, {1, 2}, {2, 3}
    }; 

    cout << (sol.isCycle(V, edges) ? "true" : "false")<< endl;

    return 0;
}