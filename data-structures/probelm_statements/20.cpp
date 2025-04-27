#include <iostream>
using namespace std;

string solve(string string1, string string2) {
    bool present[26] = {false};

    for(char ch : string2) {
        present[ch - 'a'] = true;
    }

    string res = "";
    for(char ch : string1) {
        if(!present[ch - 'a']) {
            res += ch;
        }
    }
    return res;
}

int main() {
    string string1 = "computer";
    string string2 = "cat";

    cout << solve(string1, string2);
    return 0;
}

/*
Given two strings string1 and string2, remove those characters from the first string(string1)
which are present in the second string(string2). Both strings are different and contain only
lowercase characters.
NOTE: The size of the first string is always greater than the size of the second string( |string1| &gt;
|string2|).
Input: string1 = “computer” string2 = “cat”
Output: “ompuer”
*/