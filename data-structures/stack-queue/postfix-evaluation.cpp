#include <iostream>
using namespace std;

int stackSize = 10;
char postfix[10];
int myStack[10] = {0}; // initialise stack elements to 0
int top = -1;

void push(int n) {
    if(top == stackSize - 1) {
        cout << "Stack Overflow (stack is empty)" << endl;
        return;
    } else {
        top++;
        myStack[top] = n;
    }
}

int pop() {
    if(top == -1) {
        cout << "Stack is Empty" << endl;
        return - 1;
    } else {
        int n = myStack[top];
        top--;
        return n;
    }
}

// function to check is the character is a digit
int isDigit(char ch) {
    return (ch >= '0' && ch <= '9');
}

int main() {
    int i, a, b, result, eval;
    char ch;
    cout << "Enter the postfix expression: ";
    cin >> postfix;

    for(int i = 0; postfix[i] != '\0'; i++) {
        ch = postfix[i];
        if(isDigit(ch)) {
            push(ch - '0'); // convert char digit to int
        } else {
            b = pop();
            a = pop();
            switch(ch) {
                case '+':
                    result = a + b;
                    break;
                
                case '-':
                    result = a - b;
                    break;

                case '*':
                    result = a * b;
                    break;

                case '/':
                    if(b == 0) {
                        cout << "ERROR! division not possible" << endl;
                        return 0;
                    }
                    result = a / b;
                    break;
                
                default:
                    cout << "Invalid Operator: " << ch << endl;
                    return 0;
            }
            push(result);
        }
    }

    eval = pop();
    cout << "The postfix evaluation is: " << eval << endl;
    return 0;
}