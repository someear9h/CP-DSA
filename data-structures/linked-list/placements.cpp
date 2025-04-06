#include <iostream>
using namespace std;

struct Node {
    int id;
    string name;
    string company;
    Node* next;
    
    // Constructor to initialize node
    Node(int i, string n, string c) {
        id = i;
        name = n;
        company = c;
        next = nullptr;
    }
};

class PlacementList {
private:
    Node* head;

public:
    PlacementList() { head = nullptr; }

    // Function to create a node
    void createNode(int id, string name, string company) {
        Node* newNode = new Node(id, name, company);
        if (!head) {
            head = newNode;
        } else {
            Node* temp = head;
            while (temp->next) {
                temp = temp->next;
            }
            temp->next = newNode;
        }
        cout << "Node Inserted Successfully\n";
    }
    
    // Function to display nodes
    void displayNodes() {
        if (!head) {
            cout << "No Records Found\n";
            return;
        }

        Node* temp = head;
        while (temp) {
            cout << "ID: " << temp->id << ", Name: " << temp->name << ", Company: " << temp->company << endl;
            temp = temp->next;  // Fix here
        }
    }

    // Function to insert node at a specific position
    void insertNode(int id, string name, string company, int pos) {
        Node* newNode = new Node(id, name, company);

        if (pos == 1) {
            newNode->next = head;
            head = newNode;
        } else {
            Node* temp = head;
            for (int i = 1; temp && i < pos - 1; i++) {
                temp = temp->next;
            }
            if (!temp) {
                cout << "Invalid Position\n";
                return;
            }
            newNode->next = temp->next;
            temp->next = newNode;
        }
        cout << "Node inserted at position " << pos << " successfully\n";
    }

    // Function to search for a node
    void searchNode(int id) {
        Node* temp = head;
        bool found = false;

        while (temp) {
            if (temp->id == id) {
                cout << "Record Found: " << temp->name << " Placed at: " << temp->company << endl;
                found = true;
                break;  // Stop when found
            }
            temp = temp->next;
        }

        if (!found) {
            cout << "Record Not Found!\n";
        }
    }

    // Function to delete a node
    void deleteNode(int id) {
        if (!head) {
            cout << "List Is Empty\n";
            return;
        }

        if (head->id == id) {
            Node* temp = head;
            head = head->next;
            delete temp;
            cout << "Record Deleted Successfully!\n";
            return;
        }

        Node* temp = head;
        while (temp->next && temp->next->id != id) {
            temp = temp->next;
        }
        if (!temp->next) {
            cout << "Record Not Found\n";
            return;
        }

        Node* delNode = temp->next;
        temp->next = temp->next->next;
        delete delNode;
        cout << "Record Deleted Successfully!\n";
    }

    // Function to update company of a student
    void updateNode(int id, string newCompany) {
        Node* temp = head;
        while (temp) {
            if (temp->id == id) {
                temp->company = newCompany;
                cout << "Company Updated Successfully\n";
                return;
            }
            temp = temp->next;
        }
        cout << "Record Not Found!\n";
    }

    // Function to sort the list based on ID
    void sortList() {
        if (!head || !head->next) return;
        Node* i = head;
        while (i) {
            Node* j = i->next;
            while (j) {
                if (i->id > j->id) {
                    swap(i->id, j->id);
                    swap(i->name, j->name);
                    swap(i->company, j->company);
                }
                j = j->next;
            }
            i = i->next;
        }
        cout << "List Sorted Successfully\n";
    }
};

int main() {
    PlacementList list;
    int choice, id, pos;
    string name, company;

    do {
        cout << "\nPlacement Information Menu:";
        cout << "\n1. Create Node\n2. Display Nodes\n3. Insert Node\n4. Search Node\n5. Delete Node\n6. Update Node\n7. Sort List\n8. Exit\nEnter Choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Enter ID, Name, Company: ";
                cin >> id;
                cin.ignore();
                getline(cin, name);
                getline(cin, company);
                list.createNode(id, name, company);
                break;

            case 2:
                list.displayNodes();
                break;

            case 3:
                cout << "Enter ID, Name, Company and Position: ";
                cin >> id;
                cin.ignore();
                getline(cin, name);
                getline(cin, company);
                cin >> pos;
                list.insertNode(id, name, company, pos);
                break;

            case 4:
                cout << "Enter ID to search: ";
                cin >> id;
                list.searchNode(id);
                break;

            case 5:
                cout << "Enter ID to delete: ";
                cin >> id;
                list.deleteNode(id);
                break;

            case 6:
                cout << "Enter ID to update and new Company: ";
                cin >> id;
                cin.ignore();
                getline(cin, company);
                list.updateNode(id, company);
                break;

            case 7:
                list.sortList();
                break;

            case 8:
                cout << "Exiting program...\n";
                break;

            default:
                cout << "Invalid choice! Try again.\n";
        }
    } while (choice != 8);

    return 0;
}
