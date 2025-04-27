#include <iostream>
using namespace std;

class Patient {
public:
    string name;
    int age;

    Patient() {}
    Patient(string n, int a) {
        name = n;
        age = a;
    }

    void display() {
        cout << name <<"\t" << age << endl;
    }
};

// bubble sort
void bubbleSort(Patient pats[], int n) {
    for(int i = 0; i < n - 1; i++) {
        for(int j = 0; j < n - 1 - i; j++) {
            if(pats[j].name > pats[j + 1].name) {
                Patient temp = pats[j + 1];
                pats[j + 1] = pats[j];
                pats[j] = temp;
            }
        }
    }
}

int main() {
    
    int n;
    cout << "Number of patiens: ";
    cin >> n;
    
    Patient pats[n];
    for(int i = 0; i < n; i++) {
        string name;
        int age;

        cout << "enter name for " << i + 1 << "patient: ";
        cin >> name;

        cout << "enter the age of " << name <<": ";
        cin >> age;
        
        pats[i] = Patient(name, age);
    }

    bubbleSort(pats, n);
    for(int i = 0; i < n; i++) {
        pats[i].display();
    }

    return 0;
}