#include <iostream>
using namespace std;

int solve(int arr[], int n, int target) {
    int count = 0;
    
    for(int i = 0; i < n; i++) {
        if(arr[i] == target) {
            count++;
        }
    }

    return count;
}

int main() {
    int arr[] = {1, 2, 2, 3, 3, 3, 4, 4, 5};
    int n = sizeof(arr) / sizeof(arr[0]);

    int target = 3;

    int result = solve(arr, n, target);
    if(result > 0) {
        cout << result;
    } else {
        cout << "target not found" << endl;
    }
    return 0;
}

/*
Given a sorted integer array containing duplicates, count occurrences of a given number. If the element is not found in the array, report that as well. Use Linear Search.

*/