// https://leetcode.com/problems/next-greater-element-i/

#include <iostream>
#include <stack>
#include <vector>
#include <unordered_map>
using namespace std;

vector<int> nextGreaterElement(vector<int> &nums1, vector<int> &nums2) {
    unordered_map<int, int> nextGreater;
    stack<int> st;

    // add elements of nums2 in stack
    for(int num : nums2) {
        
        while(!st.empty() && st.top() < num) {
            nextGreater[st.top()] = num;
            st.pop();
        }
        
        st.push(num);
    }

    // if elements remain in stack it means they dont have next greater element
    while(!st.empty()) {
        nextGreater[st.top()] = -1;
        st.pop();
    }

    vector<int> result;
    for(int i = 0; i < nums1.size(); i++) {
        result.push_back(nextGreater[nums1[i]]);
    }

    return result;
}

int main() {
    int n;
    cin >> n;
    vector<int> nums1(n);
    for(int i = 0; i < n; i++) {
        cin >> nums1[i];
    }

    int m;
    cin >> m;
    vector<int> nums2(m);
    for(int i = 0; i < m; i++) {
        cin >> nums2[i];
    }

    vector<int> res = nextGreaterElement(nums1, nums2);
    for(int i = 0; i < n; i++) {
        cout << res[i] << " ";
    }
    cout << endl;

    return 0;
}