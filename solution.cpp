#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    vector<int> PSE(vector<int>& arr) {
        int n = arr.size();
        stack<int> st;

        // prev smaller element
        vector<int> pse(n);
        for(int i = 0; i < n; i++) {
            while(!st.empty() && arr[st.top()] > arr[i]) {
                st.pop();
            }

            pse[i] = st.empty() ? -1 : st.top();
            st.push(i);
        }
        return pse;
    }

    vector<int> NSE(vector<int>& arr) {
        int n = arr.size();
        stack<int> st;
        
        // next smaller element
        vector<int> nse(n);
        for(int i = n-1; i>=0; i--) {
            while(!st.empty() && arr[st.top()] >= arr[i]) {
                st.pop();
            }

            nse[i] = st.empty() ? n : st.top();
            st.push(i);
        }
    }

    int sumSubarrayMins(vector<int>& arr) {
        int n = arr.size();
        vector<int> nse = NSE(arr);
        vector<int> pse = PSE(arr);

        
    }
};

int main() {
    vector<int> arr = {3,1,2,4};
    Solution sol;

    sol.sumSubarrayMins(arr);

    return 0;
}