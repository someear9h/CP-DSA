#include <iostream>
#include <stack>
using namespace std;

bool solve(string exp) {
    stack<char> st;

    for(char ch : exp) {
        if(ch == '{' || ch == '[' || ch == '(') {
            st.push(ch);
        } else {
            if(st.empty()) return false;

            if(ch == '}' && st.top() != '{' || (ch == ']' && st.top() != '[') || (ch == ')' && st.top() != '(')) {
                return false;
            }

            st.pop();
        }
    }
    return st.empty();
}

int main() {
    string exp = "[()]{}{[()()]()}";

    if(solve(exp)) {
        cout << "balanced\n";
    } else {
        cout << "Not balanced\n";
    }

    return 0;
}

/*
18.Given an expression string exp, write a program to examine whether the pairs and the orders
of “{“, “}”, “(“, “)”, “[“, “]” are correct in the given expression.
Input: exp = “[()]{}{[()()]()}”  Output: Balanced
Explanation: all the brackets are well-formed
*/
