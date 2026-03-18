/**
* someear1h 
*/

/**
 * PATTERN: Number Theory / Sieve of Eratosthenes
 * * INTUITION:
 * - Divisors come in pairs. Only perfect squares have an ODD number of divisors.
 * - To have EXACTLY three divisors, the square root of the number must be prime.
 * - Therefore, a T-Prime is strictly the square of a prime number.
 * * APPROACH:
 * - Since X <= 10^12, the prime roots will be <= 10^6.
 * - Precompute all primes up to 10^6 using the Sieve of Eratosthenes.
 * - For each query X, either check if X exists in a pre-generated set of prime squares,
 * OR (optimally) check if sqrt(X) is an integer AND sqrt(X) is prime.
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

const int MAX_LIMIT = 1000005;
vector<bool> isPrime(MAX_LIMIT, true);

void sieve() {
    isPrime[0] = isPrime[1] = false;

    for(ll i =2; i*i < MAX_LIMIT; i++) {
        if(isPrime[i]) {
            for(ll j = i*i; j < MAX_LIMIT; j+=i) {
                isPrime[j] = false;
            }
        }
    }
}

void solve() {
    ll x;
    cin >> x;

    ll r = round(sqrt(x));
    
    if(r*r == x && isPrime[r]) {
        cout << "YES\n";
    } else {
        cout << "NO\n";
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    sieve();

    int n;
    cin >> n;
    while (n--) {
        solve();
    }
    return 0;
}