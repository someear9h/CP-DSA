// https://codeforces.com/problemset/problem/1593/C

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

void solve() {
    int n, k;
    cin >> n >> k;

    vector<int> a(k);
    for(int i = 0; i < k; i++) cin >> a[i];

    sort(a.rbegin(), a.rend());

    int cnt = 0;
    long long sum = 0;

    while(cnt < a.size() && sum + n - a[cnt] < n) {
        sum += n - a[cnt];
        cnt++;
    }
    cout << cnt << endl;
}

int main() {
    int t;
    cin >> t;
    while(t--) {
        solve();
    }
    return 0;
}