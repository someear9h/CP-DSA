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

Node* findMiddle(Node* head) {
    if(!head) return nullptr;

    Node* slow = head;
    Node* fast = head;

    while(fast && fast->next) {
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

void printList(Node* head) {
    while (head) {
        cout << head->data;
        if (head->next) cout << " -> ";
        head = head->next;
    }
    cout << endl;
}

int main() {
    // Example 1: odd-length list
    Node* odd = new Node(1);
    odd->next = new Node(2);
    odd->next->next = new Node(3);
    odd->next->next->next = new Node(4);
    odd->next->next->next->next = new Node(5);

    cout << "List: ";
    printList(odd);
    cout << "Middle is: " << findMiddle(odd)->data << "\n\n";

    // Example 2: even-length list
    Node* even = new Node(1);
    even->next = new Node(2);
    even->next->next = new Node(3);
    even->next->next->next = new Node(4);
    even->next->next->next->next = new Node(5);
    even->next->next->next->next->next = new Node(6);

    cout << "List: ";
    printList(even);
    cout << "Middle is: " << findMiddle(even)->data << "\n";

    return 0;
}

/*
Given a Singly Linked List, the task is to find the middle of the linked list. If the number of nodes are even, then there would be two middle nodes, so return the second middle node.
Example:
Input: linked list = 1 -> 2 -> 3 -> 4 -> 5 Output: 3  Explanation: There are 5 nodes in the linked list and there is one middle node whose value is 3.
Input: linked list = 1 -> 2 -> 3 -> 4 -> 5 -> 6 Output: 4  Explanation: There are 6 nodes in the linked list, so we have two middle nodes: 3 and 4, but we will return the second middle node which is 4.

*/