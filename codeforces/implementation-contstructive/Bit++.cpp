#include <iostream>
#include <string>
using namespace std;

void solve() {
    int n;
    cin >> n;

    int x = 0;

    for(int i = 0; i < n; i++) {
        string s;
        cin >> s;

        if(s[0] == '+')
            x++;
        if(s[0] == '-')
            x--;
        if(s[2] == '+')
            x++;
        if(s[2] == '-')
            x--;
    }

    cout << x << endl;
}

int main() {
    solve();

    return 0;
}