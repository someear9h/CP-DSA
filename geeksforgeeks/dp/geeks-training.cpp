// https://www.geeksforgeeks.org/problems/geeks-training/1

#include <iostream>
#include <vector>
using namespace std;

class Solution{
public:
    int solve(vector<vector<int>> &arr) {
        int n = arr.size();
        vector<int> prev(4, 0);

        prev[0] = max(arr[0][1], arr[0][2]);
        prev[1] = max(arr[0][0], arr[0][2]);
        prev[2] = max(arr[0][0], arr[0][1]);
        prev[3] = max(arr[0][1], max(arr[0][2], arr[0][0]));

        for(int day = 1; day < n; day++) {
            vector<int> curr(4, 0);
            for(int lastDone = 0; lastDone < 4; lastDone++) {
                curr[lastDone] = 0;
                for(int task = 0; task <= 2; task++) {
                    if(task != lastDone) {
                        
                        curr[lastDone] = max(curr[lastDone], arr[day][task] + prev[task]);
                    }
                }
            }
            prev = curr;
        }
        return prev[3];
    }
};

int main() {
    Solution sol;
    vector<vector<int>> arr = {{1, 2, 5}, {3, 1, 1}, {3, 3, 3}};
    cout << sol.solve(arr) << endl;
    return 0;
}