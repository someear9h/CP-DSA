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

// Function to delete the middle node
void deleteMiddle(Node*& head) {
    if (!head || !head->next) {
        // Empty list or single node
        return;
    }

    Node* slow = head;
    Node* fast = head;
    Node* prev = nullptr;

    // Move fast by 2 steps and slow by 1 step
    while (fast && fast->next) {
        prev = slow;
        slow = slow->next;
        fast = fast->next->next;
    }

    // 'slow' is now pointing at the middle node, 'prev' is the node before it
    if (prev) {
        prev->next = slow->next; // Remove the middle node
        delete slow; // Free the memory
    }
}

void printList(Node* head) {
    while (head) {
        cout << head->data << " -> ";
        head = head->next;
    }
    cout << "NULL" << endl;
}

int main() {
    // Creating a list: 1->2->3->4->5
    Node* head = new Node(1);
    head->next = new Node(2);
    head->next->next = new Node(3);
    head->next->next->next = new Node(4);
    head->next->next->next->next = new Node(5);

    cout << "Before: ";
    printList(head);

    deleteMiddle(head);

    cout << "After: ";
    printList(head);

    return 0;
}
/*
Given a singly linked list, delete the middle of the linked list. For example, if the given linked list is 1->2->3->4->5 then the linked list should be modified to 1->2->4->5

*/