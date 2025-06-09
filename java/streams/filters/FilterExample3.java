package streams.filters;

import java.util.List;

class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public double getSalary() {
        return salary;
    }
}

public class FilterExample3 {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("John", 25, 50000),
                new Employee("Alice", 30, 80000),
                new Employee("Bob", 22, 40000),
                new Employee("Eve", 35, 90000)
        );

        List<Employee> filteredEmployees = employees.stream()
                .filter(emp -> emp.getAge() > 25)
                .filter(emp -> emp.getSalary() > 50000)
                .toList();

        filteredEmployees.forEach(emp -> System.out.println(emp.getName() + " - " + emp.getAge()));
    }
}