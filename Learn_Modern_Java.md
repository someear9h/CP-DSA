### 1. Data Processing (Streams, Filters & toList)

**What:** Filtering a collection to find specific elements and returning a new list.
**Before (2010 Java):**

```java
List<String> activeUsers = new ArrayList<>();
for (String user : users) {
    if (user.startsWith("A")) {
        activeUsers.add(user);
    }
}

```

**After (Modern Java):**

```java
// Uses Streams, Lambda filters, and the new .toList() (Java 16+)
List<String> activeUsers = users.stream()
                                .filter(u -> u.startsWith("A"))
                                .toList(); 

```

### 2. Primitive Array Boxing (Crucial for DSA)

**What:** Converting a primitive array (like `int[]`) into a `List<Integer>` so you can use Collections methods on it.
**Before (2010 Java):**

```java
int[] arr = {1, 2, 3};
List<Integer> list = new ArrayList<>();
for (int num : arr) {
    list.add(num); // Relies on implicit autoboxing in a loop
}

```

**After (Modern Java):**

```java
// Uses Arrays.stream() and .boxed() to wrap primitives into Objects
List<Integer> list = Arrays.stream(arr).boxed().toList();

```

### 3. Custom Sorting (Lambdas)

**What:** Sorting a list with custom logic (e.g., sorting in descending order or sorting objects by a specific field).
**Before (2010 Java):**

```java
Collections.sort(names, new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.length() - b.length();
    }
});

```

**After (Modern Java):**

```java
// Replaces the entire anonymous class with a single lambda expression
names.sort((a, b) -> a.length() - b.length());

// Or even shorter using method references:
names.sort(Comparator.comparingInt(String::length));

```

### 4. Boilerplate-Free Classes (Records)

**What:** Creating a simple, immutable class to hold data. Perfect for DTOs in enterprise backends or `Pair`/`Tuple` classes in DSA graph problems.
**Before (2010 Java):**

```java
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    // ... plus 20 more lines for equals(), hashCode(), and toString()
}

```

**After (Modern Java):**

```java
// Java 14+ Records generate constructors, getters, equals, and hashcode automatically
public record Point(int x, int y) {}

```

### 5. Passing Logic Around (Functions)

**What:** Storing a method or a block of logic in a variable so you can pass it to other methods.
**Before (2010 Java):**

```java
// You had to create an entirely new interface, then implement it
interface StringProcessor {
    Integer process(String s);
}
StringProcessor lengthProcessor = new StringProcessor() {
    public Integer process(String s) { return s.length(); }
};

```

**After (Modern Java):**

```java
// Uses java.util.function.Function
Function<String, Integer> getLength = String::length;

// Calling it:
int len = getLength.apply("Hello"); 

```

### 6. Safe Map Updates (computeIfAbsent / getOrDefault)

**What:** Updating a map safely without getting a `NullPointerException`. This is the single most useful trick for DSA frequency arrays and adjacency lists.
**Before (2010 Java):**

```java
// Building an adjacency list for a graph
if (!graph.containsKey(node)) {
    graph.put(node, new ArrayList<>());
}
graph.get(node).add(neighbor);

```

**After (Modern Java):**

```java
// Creates the list on the fly if it doesn't exist
graph.computeIfAbsent(node, k -> new ArrayList<>()).add(neighbor);

// Bonus for frequency maps:
freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

```

### 7. Type Checking and Casting (Pattern Matching)

**What:** Checking if an object is of a certain type, and then casting it to use its methods.
**Before (2010 Java):**

```java
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s.toLowerCase());
}

```

**After (Modern Java):**

```java
// Defines the casted variable 's' directly in the if-statement
if (obj instanceof String s) {
    System.out.println(s.toLowerCase());
}

```

## Modern java use in DSA and Competitve Programming

### 1. DSA: Finding the Top 'K' Most Frequent Elements

**The Problem:** You are given a large array of words, and you need to find the top 3 most frequently occurring words.
**The Modern Solution:** You can chain streams and `Collectors.groupingBy` to count the frequencies, sort the map entries by value, and extract the keys—all in one fluid pipeline.

```java
List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "apple");
int k = 2;

List<String> topWords = words.stream()
    // 1. Create a frequency map: {apple=3, banana=2, orange=1}
    .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
    .entrySet().stream()
    // 2. Sort the entries by their count in descending order
    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
    // 3. Keep only the top 'k' entries
    .limit(k)
    // 4. Extract just the word (the key) and collect to a list
    .map(Map.Entry::getKey)
    .toList(); 

```

### 2. Enterprise Backend: Mapping Database Entities to API Responses (DTOs)

**The Problem:** You fetched a list of `User` entities from your database, but you only want to send back the names and emails of active users to your frontend (like React). You don't want to expose passwords or internal IDs.
**The Modern Solution:** Define a lightweight `record` for your Data Transfer Object (DTO) and use `.map()` to transform the data on the fly.

```java
// Define the DTO in a single line (gives you constructor, getters, equals, etc.)
record UserDTO(String name, String email) {}

// Assume 'dbUsers' is fetched from your repository
List<UserDTO> apiResponse = dbUsers.stream()
    .filter(User::isActive) // Only active users
    .map(user -> new UserDTO(user.getName(), user.getEmail())) // Transform to DTO
    .toList(); 

```

### 3. DSA: Building a Graph Adjacency List

**The Problem:** You are given an array of bidirectional edges (e.g., `[[0,1], [1,2], [2,0]]`) and need to build an adjacency list to run Breadth-First Search (BFS) or Depth-First Search (DFS).
**The Modern Solution:** Use `computeIfAbsent`. It automatically creates the `ArrayList` for a node if it doesn't exist yet, eliminating the need for boilerplate `if-null` checks.

```java
int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
Map<Integer, List<Integer>> graph = new HashMap<>();

for (int[] edge : edges) {
    int u = edge[0];
    int v = edge[1];
    
    // One-liners to safely initialize the list and add the neighbor
    graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
    graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u); 
}

```

### 4. Enterprise/Hackathon APIs: Handling Missing Data Gracefully

**The Problem:** You are parsing JSON from a third-party API. Sometimes a field (like a user's bio) is missing or null, and you want to provide a default value or transform it without throwing a `NullPointerException` and crashing your app.
**The Modern Solution:** Wrap the potentially null value in `Optional`. It allows you to transform the data if it exists, or fall back to a default if it doesn't.

```java
String rawBioFromApi = fetchUserBio(userId); // Could return null

String formattedBio = Optional.ofNullable(rawBioFromApi)
    .map(String::trim) // Trims whitespace IF it's not null
    .filter(bio -> !bio.isEmpty()) // Keeps it only if it's not empty
    .orElse("This user hasn't written a bio yet."); // Default fallback

```

---

Which of these modern patterns do you find yourself needing the most right now? We can drill deeper into Streams, or explore how to apply things like `Optional` and `Records` to a specific Spring Boot or React integration you're working on.