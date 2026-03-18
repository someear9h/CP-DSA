/**
* someear1h
*/

/**
 * PATTERN: Greedy Constructive Math (Primes)
 * * INTUITION:
 * - The goal is to maximize the NUMBER of primes in the sum. 
 * - To maximize the count, we must use the absolute SMALLEST prime number available, which is 2.
 * - If 'n' is even, it can be perfectly constructed using only 2s.
 * - If 'n' is odd, we can't use only 2s (it would sum to an even number). The next smallest prime is 3. 
 * We essentially take an even number (n - 3), build it with 2s, and slap a 3 at the end.
 * * APPROACH:
 * - Check parity using bitwise AND (n & 1).
 * - EVEN: The max count is n / 2. Print '2' exactly (n / 2) times.
 * - ODD: The max count is still n / 2 (integer division). Print '2' exactly (n / 2) - 1 times, and print one '3' at the end.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    cin >> n;

    if((n & 1) == 0) {
        int times = n/2;
        cout << times << "\n";
        for(int i = 0; i < times; i++) {
            cout << 2 << " ";
        }

        cout << "\n";
    } else {
        int times = n/2;
        cout << times << "\n";
        int s = ((times * 2)-2)/2;
        for(int i = 0; i < s; i++) {
            cout << 2 << " ";
        }
        cout << 3 << "\n";
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    solve();

    return 0;
}