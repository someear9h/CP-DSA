#include <iostream>
using namespace std;

class Queue {
    int front, rear, size;
    int *arr;

public:
    Queue() {
        size = 1000;
        arr = new int[size];
        front = -1;
        rear = -1;
    }

    void enqueue(int x) {
        if(rear == size - 1) {
            cout << "Queue Overflow" << endl;
        }

        if(front == -1) front = 0;
        arr[rear++] = x;
    }

    int dequeue() {
        if(front == -1 || front > rear) {
            cout << "Queue underflow" << endl;
            return -1;
        }

        int val = arr[front];
        front++;
        return val;
    }

    void display() {
        if (front == -1 || front > rear) {
            cout << "Queue is empty!\n";
            return;
        }
        cout << "Queue: ";
        for (int i = front; i <= rear; i++) {
            cout << arr[i] << " ";
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
                cout << "Exiting... Bye!\n";
                break;

            default:
                cout << "Invalid choice!.\n";
        }

    } while (choice != 0);

    return 0;
}