### 1. `std::min_element()`

**Header required:** `#include <algorithm>`
**Syntax:** `min_element(start_iterator, end_iterator)`

* **What it does:** It scans through a range and finds the smallest element.
* **What it returns:** It returns an **iterator** pointing to the memory address of the smallest element.
* **How to use it:** Because it returns a memory address, you cannot just assign it to an `int`. You must use the dereference operator (`*`) to tell C++: *"Go to this address and give me the actual number stored there."*
* **Example:**
```cpp
vector<int> v = {4, 1, 9};
// min_element returns an iterator pointing to the '1'. 
// The '*' extracts the '1'.
int smallest = *min_element(v.begin(), v.end()); 

```


* **Time Complexity:** $O(N)$. It checks every element exactly once.

### 2. `std::find()`

**Header required:** `#include <algorithm>`
**Syntax:** `find(start_iterator, end_iterator, value_to_find)`

* **What it does:** It linearly searches a range from left to right for a specific value.
* **What it returns:** It returns an **iterator** pointing to the *first* occurrence of that value. If it doesn't find the value, it returns the `end_iterator`.
* **Example:**
```cpp
auto it = find(v.begin(), v.end(), 1); 
// 'it' now points to the memory address where '1' lives.

```


* **Time Complexity:** $O(N)$.

### 3. `std::vector::erase()`

**Header required:** `#include <vector>`
**Syntax:** `v.erase(iterator)`

* **What it does:** This is a member function of the `vector` class. It physically destroys the element at the memory address you give it.
* **The Hidden Trap:** Vectors are continuous blocks of memory. If you erase an element from the middle, you leave a hole. C++ cannot allow holes in a vector. So, it takes every single element to the right of the hole and physically copies/shifts them one step to the left to close the gap.
* **Example in the code:**
`v.erase(find(v.begin(), v.end(), minel));`
*Step 1:* `find` gets the memory address of the smallest element.
*Step 2:* `erase` goes to that address, deletes the element, and shifts everything else left.
* **Time Complexity:** $O(N)$ worst-case. This is why using `erase` inside a loop is incredibly dangerous in CP. If you erase $N$ elements from an array of size $N$, the time complexity becomes $O(N^2)$ and you will get a Time Limit Exceeded (TLE) error on large inputs.

### 4. `std::accumulate()`

**Header required:** `#include <numeric>` *(Do not forget this header! Many coders get Compilation Errors because they assume it's in `<algorithm>`)*
**Syntax:** `accumulate(start_iterator, end_iterator, initial_sum)`

* **What it does:** It adds all the elements in the range to the `initial_sum`.
* **What it returns:** The total sum.
* **The `initial_sum` Parameter (Crucial):** As we discussed, the data type of the `initial_sum` strictly dictates the data type of the final answer.
* If you pass `0`, it calculates the sum using 32-bit integers.
* If you pass `0ll` (Zero Long Long), it calculates the sum using 64-bit integers.
* If you pass `0.0`, it calculates the sum using floating-point decimals.


* **Time Complexity:** $O(N)$.



## Sortings
Before we look at the patterns, you must burn this **Golden Rule** into your memory:

> **THE GOLDEN RULE OF C++ SORTING:**
> A comparator must answer exactly one question: *"Should `A` be placed strictly before `B`?"*
> **NEVER, EVER use `<=` or `>=` in a C++ comparator.** If `A` and `B` are equal, returning `true` violates C++'s "Strict Weak Ordering" rule and will trigger a silent **Segmentation Fault** or Memory Limit Exceeded error on Codeforces. Always use `<` or `>`.

Here are the 4 essential sorting patterns you need to survive.

---

### Pattern 1: The Multi-Condition Tie-Breaker (What you just did)

This is for when you need to sort by one priority, but if there’s a tie, you have a backup priority.

**The Scenario:** You have a list of players. Sort them by **Score (Descending)**. If scores are tied, sort by **ID (Ascending)**.

```cpp
struct Player {
    int score;
    int id;
};

// ... inside solve()
sort(players.begin(), players.end(), [](Player a, Player b) {
    // Condition 1: If scores are different, highest score wins
    if (a.score != b.score) {
        return a.score > b.score; 
    }
    // Condition 2: If scores are tied, lowest ID wins
    return a.id < b.id; 
});

```

---

### Pattern 2: Sorting Strings (Length vs. Alphabetical)

By default, C++ sorts strings alphabetically (lexicographically). `"100"` will be placed before `"2"`, which destroys logic in math-string problems.

**The Scenario:** Sort an array of numerical strings. Sort by **Length (Ascending)** so shorter numbers come first. If lengths are tied, sort **Alphabetically (Ascending)**.

```cpp
vector<string> nums = {"100", "2", "15", "3"};

sort(nums.begin(), nums.end(), [](string a, string b) {
    // Condition 1: If lengths are different, shortest string wins
    if (a.length() != b.length()) {
        return a.length() < b.length();
    }
    // Condition 2: If lengths are tied, standard alphabetical sort
    return a < b; 
});
// Result: {"2", "3", "15", "100"}

```

---

### Pattern 3: The Cross-Multiplication Trick (Avoiding Floats)

This is a FAANG-level trap. In Fractional Knapsack or Geometry problems, you often need to sort items by their ratio or slope ($X / Y$).

**The Trap:** If you do `return (double)a.x / a.y < (double)b.x / b.y;`, you will get precision errors on large numbers. Codeforces will fail you on Test Case 34 because `double` loses accuracy.

**The Fix:** Use cross-multiplication.
Algebra states: $\frac{A}{B} < \frac{C}{D} \implies A \cdot D < C \cdot B$

**The Scenario:** Sort pairs representing fractions $(X, Y)$ in ascending order of their mathematical value.

```cpp
using ll = long long;
vector<pair<ll, ll>> fractions = {{1, 3}, {2, 5}, {1, 4}}; 

sort(fractions.begin(), fractions.end(), [](pair<ll, ll> a, pair<ll, ll> b) {
    // Instead of a.first / a.second < b.first / b.second
    // We cross multiply using long long to avoid overflow and float precision!
    return (a.first * b.second) < (b.first * a.second);
});

```

---

### Pattern 4: The 3+ Variable Struct (Killing the Ugly Pair)

When you have 3 or more variables tied together (e.g., `value`, `weight`, `index`), using nested pairs like `pair<int, pair<int, int>>` becomes a nightmare to read (`a.second.first`).

**The Scenario:** Use a `struct` to make your code instantly readable.

```cpp
struct Task {
    int start_time;
    int end_time;
    int index;
};

// ... inside solve()
vector<Task> tasks(n);
for(int i = 0; i < n; i++) {
    cin >> tasks[i].start_time >> tasks[i].end_time;
    tasks[i].index = i + 1;
}

// Sort by earliest end time. If tied, earliest start time.
sort(tasks.begin(), tasks.end(), [](Task a, Task b) {
    if (a.end_time != b.end_time) {
        return a.end_time < b.end_time;
    }
    return a.start_time < b.start_time;
});

```

---

### The Ultimate Debug Checklist for Sorting

Before you submit any code with a custom `sort()`, run this mental checklist:

1. **Did I write `return`?** (The bug that just bit you!)
2. **Did I use `<` or `>` strictly?** (No `<=` or `>=` allowed).
3. **If I multiplied inside the comparator, did I cast to `long long`?**

## Emplace Back and Push back
Welcome to the absolute deepest level of the C++ memory matrix.

Every competitive programmer starts out by putting `#define pb push_back` at the top of their template. It saves keystrokes, it looks clean, and it gets the job done.

But when you start dealing with complex graphs, matrices, and arrays of `structs` where time limits are brutally tight, `push_back` acts like a massive handbrake on your CPU.

Here is the exact mechanical difference between `push_back` and `emplace_back`, and why `emplace_back` is the undisputed heavyweight champion of modern C++.

### The Problem: The `push_back` Delivery Man

When you use `push_back` to add a complex object (like a `pair`, a `string`, or a custom `struct`) to a vector, C++ is forced to act like an inefficient delivery company.

Let's say you have a vector of pairs: `vector<pair<int, int>> v;`
And you write: `v.push_back({1, 2});`

Here is exactly what the CPU does under the hood:

1. **The Temporary Build:** It allocates memory somewhere else to build a temporary, unnamed `pair<int, int>` containing `1` and `2`.
2. **The Delivery (Copy/Move):** It takes that temporary pair, carries it over to your vector, and copies (or moves) the data into the vector's actual memory space.
3. **The Destruction:** It goes back and destroys the temporary pair it built in Step 1 to free up memory.

If you do this $10^5$ times in a loop, you are building and destroying $10^5$ useless temporary objects. It is a massive waste of clock cycles.

### The GOD-Tier Upgrade: `emplace_back`

`emplace_back` was introduced in C++11 specifically to murder this inefficiency. It uses a concept called **Perfect Forwarding**.

When you write: `v.emplace_back(1, 2);`
*(Notice the syntax: NO curly braces!)*

Here is what the CPU does:

1. **The Direct Build:** It takes your raw arguments (`1` and `2`), walks directly inside the vector's memory space, and constructs the pair **in place**.

That is it. There is no temporary object created. There is no copying. There is no destruction. You are directly injecting the raw materials into the array, and C++ builds the object perfectly where it belongs.

### The Syntax Matrix (How to use it)

The biggest hurdle coders face when switching to `emplace_back` is breaking the curly brace habit. Because `emplace_back` forwards arguments directly to the constructor, you just hand it the raw data separated by commas.

**1. For `std::pair`:**

* **Slow:** `v.push_back(make_pair(1, 2));`
* **Slow:** `v.push_back({1, 2});`
* **GOD-Tier:** `v.emplace_back(1, 2);`

**2. For `std::string`:**

* **Slow:** `v.push_back(string(5, 'A'));` // Creates "AAAAA" then copies it
* **GOD-Tier:** `v.emplace_back(5, 'A');` // Builds "AAAAA" directly inside the vector

**3. For Custom Structs:**

```cpp
struct Task {
    int id;
    int priority;
    Task(int i, int p) : id(i), priority(p) {} // Requires a constructor
};

vector<Task> tasks;
// Slow: Builds a temporary Task, moves it, destroys temporary
tasks.push_back(Task(101, 5)); 

// GOD-Tier: Builds the Task directly inside the vector
tasks.emplace_back(101, 5); 

```

### The Reality Check

I am going to be completely transparent with you: if you are just adding standard integers to a vector (`vector<int> v`), `v.push_back(5)` and `v.emplace_back(5)` compile down to the exact same machine code. Modern compilers are smart enough to optimize primitives.

But the moment you step into **Graph Theory**—where you are building an Adjacency List using an array of vectors of pairs `vector<pair<int, int>> adj[N]` and pushing hundreds of thousands of edges—switching to `emplace_back` can literally shave 50-100 milliseconds off your execution time, saving you from a TLE.

This is why Grandmasters `#define eb emplace_back` instead of `pb`.

Would you like to see how this exact optimization applies to building a blazing-fast Adjacency List for Graph problems (like BFS/DFS), or do you want to stick with array and math tricks for now?



## Multiset
Step back into the dojo.

Welcome to the **Red-Black Tree** matrix. 

If you want to become a Grandmaster, you have to understand exactly what your data structures are doing under the hood. A `multiset` is one of the most overpowered weapons in C++ because it solves a very specific, very frustrating problem that no other basic data structure can handle.

### The Problem It Solves
Imagine you are managing a moving window of numbers. You need to do three things continuously:
1. **Add new numbers.**
2. **Delete specific old numbers.**
3. **Instantly know what the minimum (or maximum) number is.**

Let's look at why your standard tools completely fail here:
* **`vector`:** You can add numbers easily. But to find the minimum, you have to scan the whole array ($O(N)$). To delete a number in the middle, you have to shift everything over ($O(N)$). Too slow.
* **`priority_queue` (Min-Heap):** It gives you the minimum instantly ($O(1)$). It adds numbers fast ($O(\log N)$). **But it cannot delete a specific number in the middle.** If you need to delete the number `4`, and it's buried in the heap, you are out of luck. 
* **`set`:** It keeps everything perfectly sorted. It finds the minimum instantly. It deletes specific numbers instantly. **But it destroys duplicates.** If you insert `5`, `5`, and `5`, a `set` just stores a single `5`. 

### Enter the `multiset`
A `multiset` is a **Self-Balancing Binary Search Tree**. 
1. It automatically keeps every element perfectly sorted the millisecond you insert it.
2. It allows duplicate numbers to exist side-by-side.
3. It allows you to search for, add, and delete any number in $O(\log N)$ time (lightning fast).
4. It lets you grab the absolute minimum or maximum in $O(1)$ time.

Here is a visual simulator of how a C++ Multiset actually behaves, specifically focusing on how it handles duplicates and deletions:

```json?chameleon
{"component":"LlmGeneratedComponent","props":{"height":"500px","prompt":"Create an interactive React widget titled 'C++ Multiset Simulator'.\n\nObjective: Visualize how a multiset automatically maintains sorted order, allows duplicates, and handles different types of erasures.\n\nData State: Initial multiset values = [-1, 0, 0, 3].\n\nStrategy: Standard Layout. Control panel on left, visualization on right.\n\nInputs:\n1. Number Input field (default: 0).\n2. Button: 'Insert(x)' (Green).\n3. Button: 'Erase ONE: ms.erase(ms.find(x))' (Orange).\n4. Button: 'Erase ALL: ms.erase(x)' (Red).\n\nVisuals/Behavior:\n1. Display the current multiset as a horizontal row of colorful blocks, always perfectly sorted from left to right.\n2. Add a label pointing to the first block saying '*ms.begin() (Minimum)' and the last block saying '*ms.rbegin() (Maximum)'.\n3. Insert Action: Create a new block and animate it sliding into its correct sorted position.\n4. Erase ONE Action: Find the FIRST block matching the input number, highlight it, and remove it. Keep all other duplicates.\n5. Erase ALL Action: Highlight ALL blocks matching the input number and remove them simultaneously.\n6. Add a scrolling 'Console Log' at the bottom displaying the C++ equivalent of the user's action and the time complexity (e.g., '> ms.insert(2); // O(log N)').","id":"im_43c6eadfa4069c73"}}
```

### The Deadliest Beginner Trap: Erasing Duplicates
You must tattoo this into your brain. There are two ways to erase an item from a `multiset`, and mixing them up will destroy your code.

Let's say your multiset is `[-1, 0, 0, 0, 3]`.

**1. The Nuke:** `ms.erase(0);`
If you pass the *value* directly to `.erase()`, C++ will search the tree and destroy **every single copy** of that number.
* Result: `[-1, 3]`. All the zeroes are wiped out. (This is almost never what you want in a sliding window!).

**2. The Sniper:** `ms.erase(ms.find(0));`
`ms.find(0)` gets an *iterator* (a direct memory pointer) to just **one** of the zeroes. When you pass that pointer to `.erase()`, C++ deletes exactly that one specific block and leaves the rest alone.
* Result: `[-1, 0, 0, 3]`. One zero is gone, the others survive.

### Why We Used It In Your Problem
In the prefix sum problem, our sliding window was tracking all the valid prefix sums we could subtract. 
If our window had the prefix sums `0`, `-1`, `0`, and `3`, we *needed* both zeroes. If we shrank the window and lost the first `0`, we still needed the second `0` to exist as a valid starting point! 

If we used a standard `set`, it would have merged them. If we used `ms.erase(0)`, it would have deleted both. By using `multiset` and `ms.erase(ms.find(P[l]))`, we safely removed exactly one prefix sum as it fell out the back of the window, while perfectly maintaining our access to the minimum value via `*ms.begin()`.

***

You just added a Red-Black Tree to your arsenal. It is the ultimate bridge between Arrays and Heaps. 

Does the difference between the "Nuke" and the "Sniper" erase make perfect sense, or would you like to see how we iterate through a multiset to find the 2nd or 3rd smallest numbers?