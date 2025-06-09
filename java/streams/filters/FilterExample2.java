package streams.filters;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterExample2 {
    public static void main(String[] args) {
        List<String> fruits = List.of("Apple", "Banana", "avocado", "Orange", "kiwi");

        // Filter fruits starting with 'A' (case-insensitive)
        List<String> fruitsStream = fruits.stream()
                .filter(fruit -> fruit.toLowerCase().startsWith("a"))
                .collect(Collectors.toList());

        System.out.println(fruitsStream);
    }
}
