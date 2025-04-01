#include <iostream>
#include <vector>
using namespace std;

int solve(vector<int> &arr) {
    int n = arr.size();
    int prev = arr[0];
    int prev2 = 0;

    for(int i = 1; i < n; i++) {
        int pick = arr[i];
        if(i > 1) {
            pick += prev2;
        }

        int nonPick = 0 + prev;
        int curr = max(pick, nonPick); // Maximum sum ending at the current element
        prev2 = prev;
        prev = curr;
    }
    return prev;
}

int main() {
    vector<int> arr = {2,7,9,3,1};
    cout << solve(arr) << endl;

    return 0;
}