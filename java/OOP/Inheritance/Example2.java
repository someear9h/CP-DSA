package OOP.Inheritance;

class Employee {
    public int salary = 60000;
}

class Engineer extends Employee {
    public int benefit = 10000;
}

class Example2 {
    public static void main(String[] args) {

        // Note: Object is always created of subclass and never of superclass
        Engineer e = new Engineer();
        System.out.println("Salary is: " + e.salary + "\nBenefit is: " + e.benefit);
    }
}
