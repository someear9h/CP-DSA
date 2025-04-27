// Stack – Linked List Implementation
#include <iostream>
using namespace std;

struct Node {
    int data;
    Node* next;

    Node(int d) {
        data = d;
        next = nullptr;
    }
};

class Stack {
private:
    Node* head;

public:
    Stack() {
        head = nullptr;
    }

    // push value on stack
    void push(int v) {
        Node* newNode = new Node(v);
        newNode->next = head;
        head = newNode;
    }

    // pop from stack
    int pop() {
        if(!head) {
            cout << "Stack is Empty!" << endl;
            return -1;
        }
        int val = head->data;
        Node* temp = head;
        head = head->next;
        delete temp;
        return val;
    }

    // peek function in stack (top)
    int top() {
        if(!head) {
            cout << "Stack is empty!" << endl;
            return -1;
        }

        return head->data;
    }

    bool isEmpty() {
        return head == nullptr;
    }
};

int main() {
    Stack st;

    st.push(10);
    st.push(20);
    st.push(30);

    cout << "Top is : " << st.top() << endl;
    cout << "Popped: " << st.pop() << endl;
    cout << "popped: " << st.pop() << endl;
    return 0;
}