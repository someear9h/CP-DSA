package streams.filters;

import java.util.stream.Stream;

public class FilterExample1 {
    public static void main(String[] args) {
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter even numbers only
        numbers.filter(n -> n % 2 == 0).forEach(System.out::println);  // Prints: 2, 4, 6, 8, 10
    }
}
