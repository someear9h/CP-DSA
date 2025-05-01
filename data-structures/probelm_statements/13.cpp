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

void retainDeleteNodes(Node*& head, int M, int N) {
    if (!head) return;              // 1) If the list is empty, nothing to do.

    Node* curr = head;              // 2) `curr` will scan through the list.
    Node* prev = nullptr;           // 3) `prev` will mark the last node we keep.

    // 4) Loop until we reach the end of the list
    while (curr) {
        // ————————————— Retain M nodes —————————————
        // 5) Move `curr` forward (M−1) times so that curr ends at the Mth node.
        for (int i = 1; curr && i < M; i++) {
            curr = curr->next;
        }
        if (!curr) 
            return;                 // If fewer than M nodes remain, we’re done.
        
        // 6) Now `curr` points to the last node of the block we want to keep.
        prev = curr;                // Remember it in `prev`.
        curr = curr->next;          // Move `curr` to the first node we might delete.

        // ————————————— Delete next N nodes —————————————
        // 7) Delete up to N nodes, by unlinking and freeing each one.
        for (int i = 1; curr && i <= N; i++) {
            Node* temp = curr;
            curr = curr->next;
            delete temp;
        }

        // 8) Link the last kept node (`prev`) to the node right after the deleted block (`curr`).
        prev->next = curr;
        // 9) And the while-loop repeats from this new `curr` position.
    }
}


void printList(Node* head) {
    while (head) {
        cout << head->data << " -> ";
        head = head->next;
    }
    cout << endl;
}

int main() {
    // Create a linked list: 1->2->3->4->5->6->7->8
    Node* head = new Node(1);
    head->next = new Node(2);
    head->next->next = new Node(3);
    head->next->next->next = new Node(4);
    head->next->next->next->next = new Node(5);
    head->next->next->next->next->next = new Node(6);
    head->next->next->next->next->next->next = new Node(7);
    head->next->next->next->next->next->next->next = new Node(8);

    int M = 2, N = 2;

    cout << "Original List: ";
    printList(head);

    retainDeleteNodes(head, M, N);

    cout << "Modified List: ";
    printList(head);

    return 0;
}
/*
Given a linked list and two integers M and N. Traverse the linked list such that you retain M nodes then delete next N nodes, continue the same till end of the linked list. Difficulty Level: Rookie 
Examples: 
Input:
M = 2, N = 2
Linked List: 1->2->3->4->5->6->7->8
Output:
Linked List: 1->2->5->6

Input:
M = 3, N = 2
Linked List: 1->2->3->4->5->6->7->8->9->10
Output:
Linked List: 1->2->3->6->7->8

*/