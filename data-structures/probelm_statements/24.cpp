/*
You have been given an array of size N consisting of integers. In addition you have been
given an element M you need to find and print the index of the last occurrence of this element M
in the array if it exists in it, otherwise print -1.
*/

#include <iostream>
using namespace std;

int solve(int arr[], int n, int m) {
    int lastIdx = -1;

    for(int i = 0; i < n; i++) {
        if(arr[i] == m) {
            lastIdx = i;
        }   
    }

    return lastIdx;
}

int main() {
    int n, m;
    cin >> n >> m;

    int arr[n];
    for(int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    cout << solve(arr, n, m);

    return 0;
}