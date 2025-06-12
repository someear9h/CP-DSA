#include <iostream>
#include <vector>
#include <algorithm> // min_element and max_element
using namespace std;

bool isPossible(vector<int>& arr, int days, int m, int k) {
        int flowers = 0, bouquets = 0;

        for (int i = 0; i < arr.size(); ++i) {
            if (arr[i] <= days) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }

            if (bouquets >= m) return true;
        }

        return false;
    }

    int minDays(vector<int>& bloomDay, int m, int k) {
        if (1LL * m * k > bloomDay.size()) return -1;

        int left = *min_element(bloomDay.begin(), bloomDay.end());
        int right = *max_element(bloomDay.begin(), bloomDay.end());
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isPossible(bloomDay, mid, m, k)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

int main() {
    vector<int> bloomDay = {1,10,3,10,2};
    int m = 3, k = 1;

    cout << minDays(bloomDay, m, k) << endl;
    return 0;
}