#include <iostream>
using namespace std;

class Queue {
    int front, rear, capacity;
    int *arr;

public:
    Queue() {
        capacity = 1000;
        arr = new int[capacity];
        front = -1;
        rear = -1;
    }

    void enqueue(int x) {
        if((rear + 1) % capacity == front) {
            cout << "Queue is Full" << endl;
            return;
        }

        if(front == -1) front = 0;
        rear = (rear + 1) % capacity;
        arr[rear] = x;
    }

    int dequeue() {
        if (front == -1) {
            cout << "Queue is Empty\n";
            return -1;
        }

        int val = arr[front];
        if (front == rear) {
            
            front = rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        return val;
    }

    void display() {
        if (front == -1) {
            cout << "Queue is empty!\n";
            return;
        }

        cout << "Queue: ";
        int i = front;
        while (true) {
            cout << arr[i] << " ";
            if (i == rear) break;
            i = (i + 1) % capacity;
        }
        cout << endl;
    }

};

int main() {
    Queue q;
    int choice, val;

    do {
        cout << "\n========= Queue Menu (Array) =========\n";
        cout << "1. Enqueue\n2. Dequeue\n3.Display Queue\n0. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Enter value to enqueue: ";
                cin >> val;
                q.enqueue(val);
                break;

            case 2:
                cout << "Dequeued value: " << q.dequeue() << endl;
                break;

            case 3:
                cout << "Queue Display: " << endl;
                q.display();
                break;

            case 0:
                cout << "Exit!\n";
                break;

            default:
                cout << "Invalid choice!.\n";
        }

    } while(choice != 0);

    return 0;
}