// https://codeforces.com/problemset/problem/1201/C

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool isPossible(vector<int>& arr, int n, int k, long long mid) {
    long long ops = 0;
    for (int i = n / 2; i < n; i++) {
        if (arr[i] < mid) {
            ops += mid - arr[i];
        }
        if (ops > k) return false;
    }
    return true;
}

void solve() {
    int n, k;
    cin >> n >> k;

    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    sort(a.begin(), a.end());

    long long left = a[n / 2];
    long long right = 1e18;
    long long ans = a[n / 2];

    while (left <= right) {
        long long mid = left + (right - left) / 2;
        if (isPossible(a, n, k, mid)) {
            ans = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    cout << ans << endl;
}

int main() {
    solve();
    return 0;
}
