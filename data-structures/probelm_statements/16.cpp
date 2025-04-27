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

int getNth(Node* head, int index) {
    Node* temp = head;
    int count = 0;

    while(temp) {
        if(count == index) {
            return temp->data;
        }
        count++;
        temp = temp->next;
    }

    return -1;
}

int main() {
    // create a linked list: 1->10->30->14
    Node* head = new Node(1);
    head->next = new Node(10);
    head->next->next = new Node(30);
    head->next->next->next = new Node(14);

    int index = 2;
    if(getNth(head, index) != -1) {
        cout << getNth(head, index) << endl;
    } else  {
        cout << "Index out of bounds!" << endl;
    }

    return 0;
}

/*
Write a GetNth() function that takes a linked list and an integer index and returns the data value stored in the node at that index position. 
Input:  1->10->30->14,  index = 2
Output: 30  
The node at index 2 is 30


*/