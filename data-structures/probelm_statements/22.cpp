#include <iostream>
using namespace std;

void bubbleSort(int arr[], int n, int passes) {
    // loop for passes
    for(int i = 0; i < passes; i++) {
        for(int j = 0; j < n - 1 - i; j++) {
            if(arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }

        // print the array
        cout << "After " << i + 1 << " Pass: ";
        for(int k = 0; k < n; k++) {
            cout << arr[k] << " ";
        }
        cout << "\n";
    }
} 

int main() {
    int arr[] = {19, 1, 9, 7, 3, 10, 13, 15, 8, 12};

    int n = sizeof(arr) / sizeof(arr[0]);

    cout << "Original List: \n";
    for(int i = 0; i < n; i++) {
        cout << arr[i] << " ";
    }
    cout << endl;

    int passes = 3;
    bubbleSort(arr, n, passes);
    
    return 0;
}