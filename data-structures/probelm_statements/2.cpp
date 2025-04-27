#include <iostream>
using namespace std;

int findFirstOccurrence(int arr[], int n, int target) {
    int left = 0, right = n - 1;
    int first = -1;

    while(left <= right) {
        int mid = left + (right - left) / 2;

        if(arr[mid] == target) {
            first = mid;  // Found, but continue searching to the left for first occurrence
            right = mid - 1;
        } else if(arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return first;
}

int findLastOccurrence(int arr[], int n, int target) {
    int left = 0, right = n - 1;
    int last = -1;

    while(left <= right) {
        int mid = left + (right - left) / 2;

        if(arr[mid] == target) {
            last = mid;  // Found, but continue searching to the right for last occurrence
            left = mid + 1;
        } else if(arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return last;
}

int countOccurrences(int arr[], int n, int target) {
    int first = findFirstOccurrence(arr, n, target);
    if(first == -1) {
        return 0; // Target not found
    }

    int last = findLastOccurrence(arr, n, target);

    return last - first + 1;
}

int main() {
    int arr[] = {1, 2, 2, 3, 3, 3, 4, 4, 5};
    int n = sizeof(arr) / sizeof(arr[0]);
    int target = 3;

    int result = countOccurrences(arr, n, target);

    if(result > 0) {
        cout << "Target " << target << " occurs " << result << " times." << endl;
    } else {
        cout << "Target not found." << endl;
    }

    return 0;
}
/*
Given a sorted integer array containing duplicates, count occurrences of a given number. If the element is not found in the array, report that as well. Use Binary Search.

*/