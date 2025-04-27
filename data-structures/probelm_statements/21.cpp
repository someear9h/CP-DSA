#include <iostream>
#include <stack>
using namespace std;

int solve(string str, int startIdx) {
    stack<int> st;

    for(int i = startIdx; i < str.size(); i++) {

        // if opening bracket then push the current index on stack;
        if(str[i] == '{' || str[i] == '[' || str[i] == '(') {
            st.push(i);
        } else if(str[i] == '}' || str[i] == ']' || str[i] ==')') {
            // Check if the popped opening bracket corresponds to the closing bracket
            if(!st.empty()) {
                char top = str[st.top()];
                if((str[i] == '}' && top == '{') || (str[i] == ']' && top == '[') || (str[i] == ')' && top == '(')) {
                    st.pop();
                }
            }

            if(st.empty()) {
                return i;
            }
        }
    }
    return -1;
}

int main() {
    string str = "[ABC[23]][89]";
    int index = 0;  // Starting index of the opening bracket
    
    cout << "The matching closing bracket is at index: " << solve(str, index) << endl;
    
    return 0;
}

/*
21.Given a string with brackets. If the start index of the open bracket is given, find the index of
the closing bracket. Examples:
Input : string = [ABC[23]][89]
index = 0
Output : 8
The opening bracket at index 0 corresponds to closing bracket at index 8.
We traverse given expression from given index and keep pushing starting brackets. Whenever we
encounter a closing bracket, we pop a starting bracket. If stack becomes empty at any moment, we
return that index.
*/