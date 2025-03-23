// https://leetcode.com/problems/sum-of-subarray-minimums/description/

#include <iostream>
#include <vector>
#include <stack>
using namespace std;

class Solution {
public:
    int subArrayMins(vector<int> &arr) {
        int n =  arr.size();
        vector<int> left(n), right(n);
        stack<int> st;
        const int MOD = 1e9 + 7;

        // calculate number of subarrays ending at i where arr[i] is the minimum
        for(int i = 0; i < n; i++) {
            while(!st.empty() && arr[st.top()] > arr[i]) {
                st.pop();
            }
            /*
            if stack is empty means there is no lesser element on left of arr[i] so no. of
            subarrays where arr[i] is minimum is i + 1

            if stack is not empty means st.top() is the lesser element on left of arr[i]
            */ 
            left[i] = st.empty() ? i + 1 : i - st.top();
            st.push(i);
        }

        // empty stack for right array now
        while(!st.empty()) st.pop();

        // do the same for right array
        for(int i = n - 1; i >= 0; i--) {
            while(!st.empty() && arr[st.top()] >= arr[i]) {
                st.pop();
            }

            right[i] = st.empty() ? n - i : st.top() - i;
            st.push(i);
        }

        // compute the final sum
        long ans = 0;
        for(int i = 0; i < n; i++) {
            ans = (ans + (long)arr[i] * left[i] * right[i]) % MOD; 
        }

        return (int) ans;
    }
};