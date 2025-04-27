// given a string reverse it using stack example MITAOE output EOATIM

#include <iostream>
#include <stack>
using namespace std;

string solve(string s) {
    stack<int> st;

    for(char ch : s) {
        st.push(ch);
    }

    string reverse = "";
    while(!st.empty()) {
        reverse += st.top();
        st.pop();
    }

    return reverse;
}

int main() {
    string s = "MITAOE";
    cout << solve(s);

    return 0;
}