#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    void dfs(int node, vector<vector<int>>& isConnected, vector<bool>& vis) {
        vis[node] = true;
        for(int neighbour = 0; neighbour < isConnected.size(); neighbour++) {
            if(isConnected[node][neighbour] == 1 && !vis[neighbour]) {
                dfs(neighbour, isConnected, vis);
            }
        }
    }

    int findCircleNum(vector<vector<int>>& isConnected) {
        int V = isConnected.size();
        vector<bool> vis(V, false);
        int cnt = 0;

        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                cnt++;
                dfs(i, isConnected, vis);
            }
        }
        return cnt;
    }
};

int main() {
    Solution sol;
    
    // Test Case 1: 2 provinces
    vector<vector<int>> input1 = {
        {1,1,0},
        {1,1,0},
        {0,0,1}
    };
    cout << "Test Case 1: " << sol.findCircleNum(input1) << endl;  // Output: 2
    
    // Test Case 2: 3 provinces
    vector<vector<int>> input2 = {
        {1,0,0},
        {0,1,0},
        {0,0,1}
    };
    cout << "Test Case 2: " << sol.findCircleNum(input2) << endl;  // Output: 3
    
    // Test Case 3: 1 province (fully connected)
    vector<vector<int>> input3 = {
        {1,1,1},
        {1,1,1},
        {1,1,1}
    };
    cout << "Test Case 3: " << sol.findCircleNum(input3) << endl;  // Output: 1
    
    return 0;
}