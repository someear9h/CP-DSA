// Design a Car Rental System. Arrange the list of cars according to their rental prices in ascending order using Selection Sort.

#include <iostream>
using namespace std;

class Car {
public:
    string model;
    int price;

    Car() {}

    Car(string m, int p) {
        model = m;
        price = p;
    }

    void display() {
        cout << model << "\tRs. " << price << endl;
    }

};

// selection sort
void selectionSort(Car cars[], int n) {
    for(int i = 0; i < n - 1; i++) {
        int minIdx = i;
        for(int j = i + 1; j < n; j++) {
            if(cars[j].price < cars[minIdx].price) {
                minIdx = j;
            }
        }
        if(minIdx != i) {
            Car temp = cars[i];
            cars[i] = cars[minIdx];
            cars[minIdx] = temp;
        }
    }
}

int main() {
    int n;
    cout << "enter number of cars: ";
    cin >> n;

    Car cars[n];

    for(int i = 0; i < n; i++) {
        string model;
        int price;

        cout << "Enter the model: ";
        cin >> model;

        cout << "Enter the price: ";
        cin >> price;

        cars[i] = Car(model, price);
    }

    Car c;
    selectionSort(cars, n);
    cout << "Model\tPrice" << endl;
    cout << "---------------------------\n";
    for(int i = 0; i < n; i++) {
        cars[i].display();
    }

    return 0;
}