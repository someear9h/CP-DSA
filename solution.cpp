#include <bits/stdc++.h>
using namespace std;

using ll = long long;

void solve() {
  ll n, x;
  cin >> n >> x;
  
  ll sum=0;
  ll divv = 0;
  
  for(int i = 0; i < n; i++) {
    ll num;
    cin >> num;
    divv += ((num+x-1)/x);  // ceil(a/b) -> a+b-1/b;
    sum+=num;
  }
  
  cout << (sum+x-1)/x << " " << divv << "\n";
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  
  int t;
  cin >> t;
  while(t-->0) {
    solve();
  }
  
  return 0;
}