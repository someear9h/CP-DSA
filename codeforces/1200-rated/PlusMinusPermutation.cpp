/**
* someear1h 
*/

/**
 * PATTERN: Greedy Math / Set Intersection (Inclusion-Exclusion)
 * 1. CLASS: Math / Number Theory.
 * 2. INSIGHT: Indices divisible by both 'x' and 'y' cancel out to 0. We only care about "pure" x indices (which get the largest possible numbers) and "pure" y indices (which get the smallest possible numbers).
 * 3. TRIGGER: "Divisible by X and Y", Permutations with massive constraints (N = 10^9) meaning simulation/loops will TLE.
 * 4. APPROACH: Find the LCM of X and Y to count collisions. Pure_X = (N/x) - (N/LCM). Pure_Y = (N/y) - (N/LCM). Use the arithmetic sum formula to sum the highest Pure_X elements and subtract the sum of the lowest Pure_Y elements.
 * 5. TRAP: Using a while loop to sum numbers. Always use the formula Sum(L to R) = ((L+R) * (R-L+1)) / 2 when N is up to 10^9.
*/
 
#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

// formula calculates the sum of a contiguous sequence of integers from start to end
ll sumRange(ll start, ll end) {
    ll sum = ((start + end) * (end - start +1))/2;
    return sum;
}

void solve() {
    ll n, x, y;
    cin >> n >> x >> y;

    ll meet_every = lcm(x, y); // x and y meet every 'meet_every'th index

    // how many times does x and y meet?
    ll meet_times = n / meet_every;

    // we dont conisder elements where x and y both meet because x adds it and y subtracts
    // so the next gain would be zero anyways
    ll x_fit = (n/x) - meet_times; // x's appearance wihtout meeting
    ll y_fit = (n/y) - meet_times; // y's appearance without meeting
    
    ll xSum = sumRange(n-x_fit+1, n);
    ll ySum = sumRange(1LL, y_fit);
    ll ans = xSum - ySum;

    cout << ans << "\n";
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t = 1;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}