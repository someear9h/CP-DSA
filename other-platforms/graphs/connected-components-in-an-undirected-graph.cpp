#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    void dfs(int node, vector<vector<int>>& edges, vector<bool>& vis, vector<int>& component) {
        vis[node] = true; // node visited
        component.push_back(node);

        for(auto it : edges[node]) {
            if(!vis[it]) {
                dfs(it, edges, vis, component);
            }
        }
    }

    vector<vector<int>> getComponents(int V, vector<vector<int>>& edges) {
        // build the adjacency list
        vector<vector<int>> adj(V);
        for(auto edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].push_back(v);
            adj[v].push_back(u);
        }

        vector<bool> vis(V, false);
        vector<vector<int>> res;

        for(int i = 0; i < V; i++) {
            
            if(!vis[i]) {
                vector<int> component;
                dfs(i, adj, vis, component);
                sort(component.begin(), component.end()); // sorting optional
                res.push_back(component);
            }

        }

        return res;
    }

};

int main() {
    Solution sol;

    int V = 5;

    vector<vector<int>> edges = {
        {0, 1}, {2, 1}, {3, 4}
    };

    vector<vector<int>> res = sol.getComponents(V, edges);

    for(int i = 0; i < res.size(); i++) {
        for(int j = 0; j < res[i].size(); j++) {
            cout << res[i][j] << " ";
        }
        cout << endl;
    }
    
    return 0;
}