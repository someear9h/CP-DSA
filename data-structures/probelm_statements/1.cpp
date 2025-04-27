#include <iostream>
using namespace std;

int solve(int arr[], int n) {
    int low = 0, high = n - 1;

    while(low <= high) {
        int mid = low + (high - low) / 2;
        
        if(arr[mid] == mid) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return low;
}

int main() {
    int n;
    cin >> n;

    int arr[n];
    for(int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    cout << solve(arr, n);
    return 0;
}

/*
Given a sorted array of non-negative distinct integers, find the smallest missing non-negative element in it. Use Binary Search.
*/