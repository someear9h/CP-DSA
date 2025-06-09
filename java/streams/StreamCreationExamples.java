package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamCreationExamples {
    public static void main(String[] args) {
        // 1. Creating a Stream from a List and printing its elements
        List<String> list = Arrays.asList("apple", "orange", "banana");
        Stream<String> listStream = list.stream();

        System.out.println("Fruits from List stream:");
        listStream.forEach(fruit -> System.out.println(" - " + fruit));
        // forEach is a terminal operation that performs an action for each element
        // After this operation, the stream is consumed and cannot be reused

        // 2. Creating a Stream from an Array and printing its elements
        String[] array = {"apple", "orange", "banana"};
        Stream<String> arrayStream = Arrays.stream(array);

        System.out.println("\nFruits from Array stream:");
        arrayStream.forEach(System.out::println); // Method reference version
        // System.out::println is equivalent to: x -> System.out.println(x)

        // 3. Creating a Stream from individual elements and counting them
        Stream<Integer> numberStream = Stream.of(1, 2, 3);

        System.out.println("\nNumbers from direct stream:");
        numberStream.map(n -> "Number: " + n)  // Intermediate operation
                .forEach(System.out::println); // Terminal operation
        // map() transforms each element, here we're converting numbers to strings

        // 4. Creating an infinite stream with limit and printing first elements
        Stream<Integer> infiniteStream = Stream.iterate(0, i -> i + 2).limit(10);
        // Starts at 0, each next element is previous + 2
        // limit(10) takes only first 10 elements of what would be an infinite stream

        System.out.println("\nFirst 10 even numbers:");
        infiniteStream.forEach(n -> System.out.print(n + " "));
        // Prints: 0 2 4 6 8 10 12 14 16 18
    }
}
