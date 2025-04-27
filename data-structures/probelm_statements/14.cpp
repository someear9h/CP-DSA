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

Node* lastNodeHead(Node* head) {
    Node* secLast = nullptr;
    Node* last = head;

    while(last->next) {
        secLast = last;
        last = last->next;
    }

    secLast->next = nullptr;
    last->next = head;
    return last;
}

void printList(Node* head) {
    while(head) {
        cout << head->data << " -> ";
        head = head->next;
    }
    cout << endl;
}

int main() {
    Node* head = new Node(1);
    head->next = new Node(2);
    head->next->next = new Node(3);
    head->next->next->next = new Node(4);
    head->next->next->next->next = new Node(5);

    cout << "Before: ";
    printList(head);

    head = lastNodeHead(head);

    cout << "After: ";
    printList(head);

    return 0;
}

/*
Write a function that moves the last node to the front in a given Singly Linked List.
Examples:
Input: 1->2->3->4->5   Input: 3->8->1->5->7->12 Output: 5->1->2->3->4  Output: 12->3->8->1->5->7  

*/