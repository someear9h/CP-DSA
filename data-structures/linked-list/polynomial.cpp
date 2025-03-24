#include <iostream>
using namespace std;

class Node {
public:
    int coefficient;
    int exponent;
    Node* next;

    Node(int coeff, int exp) {
        coefficient = coeff;
        exponent = exp;
        next = nullptr;
    }
};

class Polynomial {
private:
    Node* head;

public:
    Polynomial() {
        head = nullptr;
    }

    // insert a node in polynomial
    void insert (int coeff, int exp) {
        Node* newNode = new Node(coeff, exp);
        if(!head) {
            head = newNode;
            return;
        }

        Node* temp = head;
        while(temp->next) {
            temp = temp->next;
        }
        temp->next = newNode;
    }

    void display() {
        Node* temp = head;
        while(temp) {
            cout << temp->coefficient << "x^" << temp->exponent;
            temp = temp->next;
            if(temp) cout << " + ";
        }
        cout << endl;
    }
};

int main() {
    Polynomial poly;

    poly.insert(4, 2);
    poly.insert(3, 1);
    poly.insert(20, 0);

    cout << "Polynomial: ";
    poly.display();
    
    return 0;
}