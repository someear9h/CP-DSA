#include <iostream>
using namespace std;

class Stack {
    int size;
    int *arr;
    int top;

public:
    Stack() {
        size = 1000;
        arr = new int[size];
        top = -1;
    }

    void push(int x) {
        if (top == size - 1) {
            cout << "Stack Overflow!\n";
            return;
        }
        arr[++top] = x;
    }

    int pop() {
        if (top == -1) {
            cout << "Stack Underflow!\n";
            return -1;
        }
        return arr[top--];
    }

    void display() {
        if (top == -1) {
            cout << "Stack is empty!\n";
            return;
        }
        cout << "Stack: ";
        for (int i = top; i >= 0; i--) {
            cout << arr[i] << " ";
        }
        cout << endl;
    }
};

int main() {
    Stack s;
    int choice, val;

    do {
        cout << "\n========= Stack Menu =========\n";
        cout << "1. Push\n2. Pop\n3. Display\n0. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Enter value to push: ";
                cin >> val;
                s.push(val);
                break;

            case 2:
                cout << "Popped value: " << s.pop() << endl;
                break;

            case 3:
                cout << "Stack Display: " << endl;
                s.display();
                break;

            
            case 0:
                cout << "Exit!\n";
                break;

            default:
                cout << "Invalid choice! Try again.\n";
        }

    } while (choice != 0);

    return 0;
}
