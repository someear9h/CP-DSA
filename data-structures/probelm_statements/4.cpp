#include <iostream>
using namespace std;

int binarySearch(int arr[], int n, int target) {
    int left = 0, right = n - 1;

    while(left <= right) {
        int mid = left + (right - left) / 2;

        if(arr[mid] == target) {
            return mid;
        } else if(arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return -1;
}

int main() {
    int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int n = sizeof(arr) / sizeof(arr[0]);
    int target = 5;

    int result = binarySearch(arr, n, target);

    if(result == -1) {
        cout << "Target Not found!" << endl;
    } else {
        cout << "Target found at index: " << result << endl;
    }

    return 0;
}

/*
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search the target in nums. If the target exists, then return its index.                         Otherwise, return -1.

*/