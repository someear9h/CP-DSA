import java.util.*;

public class ModernCollectionMethods {
    public static void main(String[] args) {
        List<String> fruits = List.of("Apple", "Banana", "Cherry");
        // fruits.add("Kiwi"); // will give an error
        System.out.println("Fruits: " + fruits);

        List<String> mutableFruits = new ArrayList<>(List.of("Apple", "Banana", "Cherry"));
        mutableFruits.add("Kiwi");
        mutableFruits.set(0, "elppa");

        System.out.println("Mutable Fruits: " + mutableFruits);

        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5);
        System.out.println("Set: " + numbers);

        Map<Integer, String> rankMap = Map.of(
                1, "GrandMaster",
                2, "Master",
                3, "professional");

        System.out.println("rankMap content: " + rankMap.get(1));

        Map<Integer, String> mp = Map.ofEntries(
                Map.entry(1, "Samarth"),
                Map.entry(2, "Sanket"),
                Map.entry(3, "Somnath"));

        System.out.println("ofEntries working: " + mp.get(1));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.set(0, 100);

        System.out.println("List using asList(): " + list);

    }
}