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

void deleteSecondMiddle(Node*& head) {
    // Check if list has fewer than two elements
    if(!head || !head->next || !head->next->next) {
        return;
    }

    Node* slow = head;
    Node* fast = head;
    
    // Move fast pointer 2 steps at a time and slow pointer 1 step at a time
    // We stop once the fast pointer has passed the list or is at the end
    while(fast && fast->next && fast->next->next) {
        slow = slow->next;
        fast = fast->next->next;
    }

    // Now slow is at the first middle node
    // We need to delete the node after slow (which is the second middle node)
    if(slow && slow->next) {
        Node* temp = slow->next;
        slow->next = temp->next;
        delete temp;  // Delete the second middle node
    }
}

void printList(Node* head) {
    while(head) {
        cout << head->data << " -> ";
        head = head->next;
    }
    cout << "NULL" << endl;
}

int main() {
    Node* head = new Node(1);
    head->next = new Node(2);
    head->next->next = new Node(3);
    head->next->next->next = new Node(4);
    head->next->next->next->next = new Node(5);
    head->next->next->next->next->next = new Node(6);

    cout << "Before: ";
    printList(head);

    deleteSecondMiddle(head);

    cout << "After: ";
    printList(head);

    return 0;
}

/*
If there are even nodes, then there would be two middle nodes, we need to delete the second middle element. For example, if given linked list is 1->2->3->4->5->6 then it should be modified to 1->2->3->5->6.

*/
