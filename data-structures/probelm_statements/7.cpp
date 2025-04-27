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

void deleteLastOccurrence(Node*& head, int key) {
    if(!head) return;
    
    Node* curr = head;
    Node* prev = nullptr;
    Node* lastPrev = nullptr;


    while(curr) {
        if(curr->data == key) {
            lastPrev = prev;
        }
        
        prev = curr;
        curr = curr->next;
    }

    if(!lastPrev && head->data != key) {
        return;
    }

    if(!lastPrev) {
        Node* delNode = head;
        head = head->next;
        delete delNode;
    } else {
        Node* delNode = lastPrev->next;
        lastPrev->next = lastPrev->next->next;
        delete delNode;
    }
}

// Helper to print the list
void printList(Node* head) {
    while (head) {
        cout << head->data;
        if (head->next) cout << " -> ";
        head = head->next;
    }
    cout << endl;
}

int main() {
    // Example: 1->2->3->2->4->2->5, delete last occurrence of 2
    Node* head = new Node(1);
    head->next = new Node(2);
    head->next->next = new Node(3);
    head->next->next->next = new Node(2);
    head->next->next->next->next = new Node(4);
    head->next->next->next->next->next = new Node(2);
    head->next->next->next->next->next->next = new Node(5);

    cout << "Before: ";
    printList(head);

    deleteLastOccurrence(head, 2);

    cout << "After deleting last occurrence of 2: ";
    printList(head);

    return 0;
}

/*
Delete last occurrence of an item from linked list - Using pointers, loop through the whole list and keep track of the node prior to the node containing the last occurrence key using a special pointer. After this just store the next of next of the special pointer, into to next of special pointer to remove the required node from the linked list.

*/