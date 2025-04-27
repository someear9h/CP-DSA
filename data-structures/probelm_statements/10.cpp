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

void mergeAlternate(Node*& first, Node*& second) {
    Node* p1 = first;
    Node* p2 = second;
    Node* p1_next;
    Node* p2_next;

    while(p1 && p2) {
        p1_next = p1->next;
        p2_next = p2->next;

        // Insert p2 after p1
        p1->next = p2;
        
        // If there is a next node for p1, set p2->next to that
        if(p1_next != nullptr) {
            p2->next = p1_next;
        }

        // Move p1 and p2 forward
        p1 = p1_next;
        p2 = p2_next;
    }

    // Set second list to nullptr as it's now empty
    second = nullptr;
}

void printList(Node* head) {
    while(head != nullptr) {
        cout << head->data << " -> ";
        head = head->next;
    }
    cout << "NULL" << endl;
}

int main() {
    // First List
    Node* first = new Node(5);
    first->next = new Node(7);
    first->next->next = new Node(17);
    first->next->next->next = new Node(13);
    first->next->next->next->next = new Node(11);

    // Second List
    Node* second = new Node(12);
    second->next = new Node(10);
    second->next->next = new Node(2);
    second->next->next->next = new Node(4);
    second->next->next->next->next = new Node(6);

    cout << "First List before merge: ";
    printList(first);

    cout << "Second List before merge: ";
    printList(second);

    mergeAlternate(first, second);

    cout << "First List after merge: ";
    printList(first);

    cout << "Second List after merge: ";
    printList(second);

    return 0;
}

/*
Given two linked lists, insert nodes of second list into first list at alternate positions
 of first list.  For example, if first list is 5->7->17->13->11 and 
 second is 12->10->2->4->6, the first list should become 
 5->12->7->10->17->2->13->4->11->6 and second list should become empty.

*/