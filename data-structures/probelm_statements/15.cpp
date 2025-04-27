#include <iostream>
using namespace std;

struct Node {
    int data;
    Node* next;
    Node(int d){
        data = d;
        next = nullptr;
    }
};

// Delete from beginning
void deleteFromBeginning(Node*& head) {
    if (!head) return;
    Node* temp = head;
    head = head->next;
    delete temp;
}

// Delete from end
void deleteFromEnd(Node*& head) {
    if (!head) return;
    if (!head->next) {
        delete head;
        head = nullptr;
        return;
    }
    Node* cur = head;
    while (cur->next->next) {
        cur = cur->next;
    }
    delete cur->next;
    cur->next = nullptr;
}

// Delete middle node (no prev pointer needed)
void deleteMiddle(Node*& head) {
    if (!head || !head->next) return;
    if (!head->next->next) {
        // exactly two nodes: delete the second
        delete head->next;
        head->next = nullptr;
        return;
    }
    Node* slow = head;
    Node* fast = head;
    while (fast && fast->next) {
        fast = fast->next->next;
        slow = slow->next;
    }
    // slow now at middle; delete slow by copying from next
    Node* toDelete = slow->next;
    slow->data = toDelete->data;
    slow->next = toDelete->next;
    delete toDelete;
}

// Print without mutating head
void printList(Node* head) {
    while (head) {
        cout << head->data << " -> ";
        head = head->next;
    }
    cout << "nullptr\n";
}

int main() {
    // build list: 1->10->30->14
    Node* head = new Node(1);
    head->next = new Node(10);
    head->next->next = new Node(30);
    head->next->next->next = new Node(14);

    cout << "Original List: ";
    printList(head);

    deleteFromBeginning(head);
    cout << "After deleting from beginning: ";
    printList(head);

    deleteFromEnd(head);
    cout << "After deleting from end: ";
    printList(head);

    deleteMiddle(head);
    cout << "After deleting middle node: ";
    printList(head);

    return 0;
}

/*
Delete from a Linked List:- You can delete an element in a list from:
Beginning              End                 Middle

*/