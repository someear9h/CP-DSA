package methods.varargs;

public class Eg1 {
    public static void main(String[] args) {

        /*
        * var args -> allows a method to accept varying number of amounts,
        * makes methods more flexible
        * */

        System.out.println(average(1, 2, 3, 4));
    }

    static double average(double... numbers) {
        if(numbers.length == 0) return 0;
        double sum = 0;

        for(double num : numbers) {
            sum += num;
        }

        return sum/numbers.length;
    }
}
