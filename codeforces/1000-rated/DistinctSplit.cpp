/**
* someear1h 
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

/**
alternative solution without using sets (which are inefficient)

    vector<int> left(n), right(n);
    vector<bool> seen(26, false); // Instant O(1) memory lookup
    int distinct_count = 0;
    
    // Step 1: Left Sweep
    for(int i = 0; i < n; i++) {
        int char_index = s[i] - 'a'; // Maps 'a' to 0, 'z' to 25
        if(!seen[char_index]) {
            seen[char_index] = true;
            distinct_count++;
        }
        left[i] = distinct_count;
    }
    
    // Reset for the right sweep
    fill(seen.begin(), seen.end(), false);
    distinct_count = 0;
    
    // Step 2: Right Sweep
    for(int i = n - 1; i >= 0; i--) {
        int char_index = s[i] - 'a';
        if(!seen[char_index]) {
            seen[char_index] = true;
            distinct_count++;
        }
        right[i] = distinct_count;
    }
*/

/**
- we go one time through left counting the number of distinct characters till i
- left[i] represents distinct characters ending at i and right[i] represents distinct characters
starting at i
- we then look for maximum, thats it
*/
void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;

    vector<int> left(n), right(n);
    unordered_set<char> seto;
    
    for(int i = 0;i < n; i++) {
        seto.insert(s[i]);
        left[i] = seto.size();
    }
    
    seto.clear();
    
    for(int i= n-1; i>=0;i--) {
        seto.insert(s[i]);
        right[i] = seto.size();
    }
    
    int total = 0;
    for(int i = 0; i < n-1; i++) {
        total = max(total, left[i]+right[i+1]);
    }

    cout << total << "\n";
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