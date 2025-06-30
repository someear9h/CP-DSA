#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Solution {
  public:
    bool isCycle(int V, vector<vector<int>>& edges) {
        // Code here
        vector<vector<int>> adj(V);
        for(auto edge : edges) {
            int u = edge[0], v = edge[1];
            adj[u].push_back(v);
            adj[v].push_back(u);
        }
        
        vector<bool> vis(V, false);
        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
               if(bfs(i, adj, vis)) return true;
            }
        }
        return false;
    }
    
    bool bfs(int startNode, vector<vector<int>>& adj, vector<bool>& vis) {
        vis[startNode] = true; // visited
        queue<pair<int, int>> q;
        q.push({startNode, -1});
        
        while(!q.empty()) {
            int currNode = q.front().first;
            int parent = q.front().second;
            q.pop();
            
            for(auto adjNode : adj[currNode]) {
                if(!vis[adjNode]) {
                    vis[adjNode] = true;
                    q.push({adjNode, currNode});
                } else if(parent != adjNode) {
                    return true;
                }
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