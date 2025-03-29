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

    // function to insert a term into the polynomial
    void insert(int coeff, int exp) {
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

    // function to display the polynomial
    void display() {
        if(!head) {
            cout << "Polynomial is empty" << endl;
            return;
        }

        Node* temp = head;
        while(temp) {
            cout << temp->coefficient << "x^" << temp->exponent;
            temp = temp->next;

            if(temp) cout << "+";
        }
        cout << endl;
    }

    // function to add two polynomials
    static Polynomial add(Polynomial &poly1, Polynomial &poly2) {
        Polynomial result;
        Node* head1 = poly1.head;
        Node* head2 = poly2.head;
        Node* temp = nullptr;

        while(head1 != nullptr && head2 != nullptr) {
            int coeff, exp;

            if(head1->exponent > head2->exponent) {
                coeff = head1->coefficient;
                exp = head1->exponent;
                head1 = head1->next;
            } else if(head1->exponent < head2->exponent) {
                coeff = head2->coefficient;
                exp = head2->exponent;
                head2 = head2->next;
            } else {
                // when exponents are equal add coefficients
                coeff = head1->coefficient + head2->coefficient;
                exp = head1->exponent;
                head1 = head1->next;
                head2 = head2->next;
            }

            if(coeff != 0) {
                // avoid inserting zero coefficient terms
                result.insert(coeff, exp);
            }
        }

        // add remaining terms from the first polynomial
        while(head1) {
            result.insert(head1->coefficient, head1->exponent);
            head1 = head1->next;
        }

            // add remaining terms from the second polynomial
        while(head2) {
            result.insert(head2->coefficient, head2->exponent);
            head2 = head2->next;
        }

        return result;
    }
};

// main function with menu driven approach
int main() {
    Polynomial poly1, poly2, result;
    int choice;

    do{
        cout << "\nPolynomial Operations Menu:\n";
        cout << "1. Insert term in first polynomial\n";
        cout << "2. Insert term in second polynomial\n";
        cout << "3. Display first polynomial\n";
        cout << "4. Display second polynomial\n";
        cout << "5. Add polynomials and display result\n";
        cout << "6. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        int coeff, exp;
        switch(choice) {
            case 1:
                cout << "Enter Coefficient and exponent: ";
                cin >> coeff >> exp;
                poly1.insert(coeff, exp);
                break;
            
                case 2:
                cout << "Enter coefficient and exponent: ";
                cin >> coeff >> exp;
                poly2.insert(coeff, exp);
                break;

            case 3:
                cout << "First Polynomial: ";
                poly1.display();
                break;

            case 4:
                cout << "Second Polynomial: ";
                poly2.display();
                break;

            case 5:
                result = Polynomial::add(poly1, poly2);
                cout << "Resultant Polynomial: ";
                result.display();
                break;

            case 6:
                cout << "Exiting program....." << endl;
                break;

            default:
                cout << "Invalid choice! Try again." << endl;
        }
    } while(choice != 6);

    return 0;
}