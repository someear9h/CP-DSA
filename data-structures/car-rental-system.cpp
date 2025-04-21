#include <iostream>
#include <vector>
#include <string>
using namespace std;

class Car {
public:
    string carId;
    string model;
    string type;
    float pricePerDay;
    bool isAvailable;

    Car(string id, string mdl, string tp, float ppd) {
        carId = id;
        model = mdl;
        type = tp;
        pricePerDay = ppd;
        isAvailable = true;
    }
};

class User {
public:
    string name;
    string rentCarId;
    int days;
    float totalCost;

    User(string uname, string rci, int days, float cost) {
        name = uname;
        rentCarId = rci;
        this->days = days;
        totalCost = cost;
    }

    void showRental() {
        cout << "Name: " << name
             << ", Car ID: " << rentCarId
             << ", Days: " << days
             << ", Total Cost: ₹" << totalCost << "\n";
    }
};

class RentalSystem {
private:
    vector<Car> cars;
    vector<User> users;

public:
    // add a car
    void addCar(Car car) {
        cars.push_back(car);
    }

    // show available cars
    void showAvailableCars() {
        cout << "\nAvailable Cars:\n";
        bool found = false;
        for (Car &car : cars) {
            if (car.isAvailable) {
                cout << "Car ID: " << car.carId
                     << ", Model: " << car.model
                     << ", Type: " << car.type
                     << ", ₹" << car.pricePerDay << "/day\n";
                found = true;
            }
        }
        if (!found) cout << "No cars available right now.\n";
    }

    // filter cars by type
    void filterCarsByType(string type) {
        cout << "\nAvailable " << type << "s:\n";
        bool found = false;
        for(Car &car : cars) {
            if (car.isAvailable && car.type == type) {
                cout << "Car ID: " << car.carId
                     << ", Model: " << car.model
                     << ", ₹" << car.pricePerDay << "/day\n";
                found = true;
            }
        }
        if (!found) cout << "No " << type << "s available currently.\n";
    }

    // renting a car
    void rentCar(string userName) {
        showAvailableCars();
        string carId;
        cout << "Enter carId to rent: ";
        cin >> carId;

        for (Car &car : cars) {
            if (car.carId == carId && car.isAvailable) {
                int days;
                cout << "Enter number of days: ";
                cin >> days;

                float totalCost = car.pricePerDay * days;
                car.isAvailable = false;
                users.push_back(User(userName, carId, days, totalCost));
                cout << "Car rented successfully! Total cost: ₹" << totalCost << "\n";
                return;
            }
        }
        cout << "Car not found or already rented." << endl;
    }

    // return a car
    void returnCar(string userName) {
        for(auto it = users.begin(); it != users.end(); it++) {
            if(it->name == userName) {
                for(Car &car : cars) {
                    if(car.carId == it->rentCarId) {
                        car.isAvailable = true;
                        cout << "car returned successufully" << endl;
                        users.erase(it);
                        return;
                    }

                }
            }
        }
        cout << "No car found for user: " << userName << "\n";
    }

    // show rental history
    void showAllRentals() {
        cout << "\nRental History:\n";
        if (users.empty()) {
            cout << "No current rentals.\n";
            return;
        }
        for (User &user : users) {
            user.showRental();
        }
    }
};

int main() {
    RentalSystem system;

    // Adding sample cars
    system.addCar(Car("C101", "Hyundai Creta", "SUV", 2500));
    system.addCar(Car("C102", "Honda City", "Sedan", 2200));
    system.addCar(Car("C103", "Maruti Alto", "Hatchback", 1500));
    system.addCar(Car("C104", "Toyota Innova", "SUV", 2700));
    system.addCar(Car("C105", "Tata Nexon", "SUV", 2400));
    system.addCar(Car("C106", "Kia Seltos", "SUV", 2600));

    int choice;
    string userName;

    while (true) {
        cout << "\n===== Car Rental Management System =====\n";
        cout << "1. Show All Available Cars\n";
        cout << "2. Filter Cars by Type\n";
        cout << "3. Rent a Car\n";
        cout << "4. Return a Car\n";
        cout << "5. View Current Rentals\n";
        cout << "6. Exit\n";
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                system.showAvailableCars();
                break;
            case 2:
                {
                    string type;
                    cout << "Enter type (SUV/Sedan/Hatchback): ";
                    cin >> type;
                    system.filterCarsByType(type);
                }
                break;
            case 3:
                cout << "Enter your name: ";
                cin >> userName;
                system.rentCar(userName);
                break;
            case 4:
                cout << "Enter your name: ";
                cin >> userName;
                system.returnCar(userName);
                break;
            case 5:
                system.showAllRentals();
                break;
            case 6:
                cout << "Thank you for using the system!\n";
                return 0;
            default:
                cout << "Invalid choice. Try again.\n";
        }
    }

    return 0;
}