// Given a postfix expression, the task is to evaluate the postfix expression.

#include <iostream>
#include <stack>
using namespace std;

int postfixEvaluation(string exp) {
    stack<int> st;

    for(char ch : exp) {
        // if the char is digit then push on the stack
        if(isdigit(ch)) {
            st.push(ch - '0');
        } else {
            int val2 = st.top(); st.pop();
            int val1 = st.top(); st.pop();

            switch(ch) {
                case '+': st.push(val1 + val2); break;
                case '-': st.push(val1 - val2); break;
                case '*': st.push(val1 * val2); break;
                case '/': st.push(val1 / val2); break;
            }
        }
    }

    return st.top();  // the top of the stack after the loop is the answer
}

int main() {
    string exp = "231*+9-";
    cout << postfixEvaluation(exp) << endl;

    return 0;
}