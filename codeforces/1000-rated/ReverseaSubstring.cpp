/**
* someear1h
*/

/**
 * PATTERN: Greedy Adjacent Swap / Localized Inversion Check
 * 1. CLASS: String Manipulation / Greedy.
 * 2. INSIGHT: If you only need to find ANY valid operation to make a sequence smaller, look for the smallest possible localized change (a length-2 substring).
 * 3. TRIGGER: "Lexicographically smaller", "Choose EXACTLY ONE substring/subarray", "Any valid answer".
 * 4. APPROACH: Scan linearly. If s[i] > s[i+1], an inversion exists. Reverse just that pair. If no adjacent inversion exists, the array is perfectly sorted and the operation is impossible.
*/

#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n;
    cin>> n;
    string s;
    cin >> s;

    for(int i = 0; i < n-1; i++) {
        if(s[i] > s[i+1]) {
            cout << "YES\n";
            cout << i+1 << " " << i+2 << "\n";
            return;
        }
    }

    cout << "NO\n";
}


// stack solution implemented all by me, overkill? yes but its my idea and code, works 
// brillianto as hell

/**
just loop through the string and for every char find the next smaller char and then u can reverse 
them which will, thats it
*/
// void solve() {
//     int n;
//     cin >> n;

//     string s;
//     cin >> s;

//     stack<int> st;
//     vector<int> nse(n);
//     for(int i = n-1; i >=0; i--) {
//         while(!st.empty() && (s[i] - 'a') <= (s[st.top()] - 'a')) {
//             st.pop();
//         }

//         nse[i] = st.empty() ? -1 : st.top();
//         st.push(i);
//     }

//     for(int i = 0; i < n;i++) {
//         if(nse[i] != -1) {
//             // small element exists at nse[i]
//             cout << "YES\n";
//             cout << i+1 << " " << nse[i]+1 << "\n";
//             return;
//         }
//     }

//     cout << "NO\n";
// }

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t =1;
    // cin >> t;
    while(t-->0) {
        solve();
    }

    return 0;
}