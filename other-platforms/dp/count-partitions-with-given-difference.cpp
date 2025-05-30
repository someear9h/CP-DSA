// https://www.naukri.com/code360/problems/partitions-with-given-difference_3751628
// tabulation approach

#include <iostream>
#include <vector>
using namespace std;

class Solution
{
public:
    const int mod = (int)1e9 + 7;
    int f(vector<int> &arr, int d)
    {
        int n = arr.size();
        vector<vector<int>> dp(n, vector<int>(d + 1, 0));

        // base cases
        if (arr[0] == 0)
        {
            dp[0][0] = 2;
        }
        else
        {
            dp[0][0] = 1;
        }

        if (arr[0] != 0 && arr[0] <= d)
        {
            dp[0][arr[0]] = 1;
        }

        for (int idx = 1; idx < n; idx++)
        {
            for (int target = 0; target <= d; target++)
            {
                int notTaken = dp[idx - 1][target];
                int taken = 0;
                if (arr[idx] <= target)
                {
                    taken = dp[idx - 1][target - arr[idx]];
                }

                dp[idx][target] = (taken + notTaken) % mod;
            }
        }
        return dp[n - 1][d];
    }

    int countPartitions(int n, int d, vector<int> &arr)
    {
        n = arr.size();
        int totSum = 0;
        for (int i = 0; i < n; i++)
        {
            totSum += arr[i];
        }

        // base cases
        if (totSum - d < 0)
            return 0;
        if ((totSum - d) % 2 == 1)
            return 0;

        int s2 = (totSum - d) / 2;

        return f(arr, s2);
    }
};

int main()
{
    Solution sol;
    vector<int> arr = {5, 2, 6, 4};
    int d = 3;
    cout << sol.countPartitions(arr.size(), d, arr) << endl;

    return 0;
}