// .Queue – Linked List Implementation

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

class Queue {
private:
    Node* front;
    Node* rear;

public:
    Queue() {
        front = nullptr;
        rear = nullptr;
    }

    void enqueue(int val) {
        Node* newNode = new Node(val);

        if (rear == nullptr) {
            rear = front = newNode;
            return;
        }

        rear->next = newNode;
        rear = newNode;
    }

    // dequeue
    int dequeue() {
        // if queue is empty
        if (front == nullptr) {
            cout << "Queue is Empty!" << endl;
            return -1;
        }

        Node* temp = front;
        int val = front->data;
        front = front->next;

        if (front == nullptr) {
            rear = nullptr;
        }

        delete temp;
        return val;
    }

    int frontElement() {
        if (front == nullptr) {
            cout << "Queue is Empty!" << endl;
            return -1;
        }

        return front->data;
    }

    bool isEmpty() {
        return front == nullptr;
    }

    void printQueue() {
        while (!isEmpty()) {
            cout << dequeue() << " ";
        }
        cout << endl;
    }
};

int main() {
    Queue q;

    // Test the queue operations
    q.enqueue(10);
    q.enqueue(20);
    q.enqueue(30);
    q.enqueue(40);

    cout << "Front element is: " << q.frontElement() << endl;  // Should be 10

    cout << "Dequeued elements: ";
    q.printQueue();  // Now calling it correctly
    
    // Testing empty queue case
    cout << "Dequeued from empty queue: " << q.dequeue() << endl;

    return 0;
}
