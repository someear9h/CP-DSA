### 1. The Prime Rule: "You cannot build a prime from scraps"

If I ask you to make a product divisible by **3** (a prime number), you are looking for the prime factor `3`.

Think of prime numbers as unbreakable LEGO bricks. You cannot combine two different bricks to magically create a `3`.

* **Example:** Let's take the array `[4, 5]`.
* The product is `4 * 5 = 20`.
* Divide 20 by 3? You get a remainder. Why? Because the prime factorization of 4 is `2 * 2`, and 5 is just `5`. There is no `3` hiding in there.
* **The Takeaway:** You cannot say, "I'll add +1 to the 4, and +1 to the 5." That gives you `[5, 6]`. Sure, the 6 has a `3` in it now, but you wasted an operation on the 4! To get a prime factor into the final product, **you must force at least one individual number to become a multiple of that prime.**

### 2. The Primes (k = 2, 3, 5): The "Solo Carry" Strategy

Because of the rule above, when **k** is 2, 3, or 5, teamwork is impossible. You must pick exactly **one** number in the array and carry it to the finish line. We just calculate the cost for every single number to reach the next multiple of **k**, and pick the cheapest one.

* **Example:** `k = 5`, Array = `[12, 16, 24]`
* How much to make 12 a multiple of 5? `12 -> 13 -> 14 -> 15` (**3 ops**)
* How much to make 16 a multiple of 5? `16 -> 17 -> 18 -> 19 -> 20` (**4 ops**)
* How much to make 24 a multiple of 5? `24 -> 25` (**1 op**)


* **The Result:** We take the minimum. We spend **1 operation** on the 24. The new array is `[12, 16, 25]`. The product is `4800`. 4800 is perfectly divisible by 5. We completely ignored the 12 and 16.

### 3. The Composite Trap (k = 4): The "Teamwork" Loophole

Here is where the matrix breaks. **4 is not a prime.** It is `2 * 2`.
Because it is made of two smaller pieces, we suddenly have a loophole. We don't *have* to find a number and push it all the way to 4, 8, 12, etc. We can just find **two separate numbers** and make sure they both carry a single `2`. (And any number that carries a `2` is just an **even number**).

Let's look at a deadly test case where coders who only use the Prime strategy get a Wrong Answer:

* **Example:** `k = 4`, Array = `[5, 7, 9]`

**Path A: The "Solo Carry" (Treating 4 like a prime)**
Let's try to push one number all the way to a multiple of 4 (4, 8, 12...).

* `5` needs to reach `8`. Cost: **3 ops**.
* `7` needs to reach `8`. Cost: **1 op**.
* `9` needs to reach `12`. Cost: **3 ops**.
* *Path A Minimum Cost:* **1 operation** (Pushing 7 to 8).
* *Let's check it:* Array becomes `[5, 8, 9]`. Product = 360. 360 / 4 = 90. It works! Cost = 1.

**Path B: The "Teamwork" Loophole**
Wait. Can we beat a cost of 1? We need two even numbers. Let's look at the original array: `[5, 7, 9]`.

* How many even numbers do we have right now? **Zero.**
* How much does it cost to make a number even? Just **1 op** (e.g., 5 -> 6).
* Since we need two even numbers, we can increment the 5 and the 7.
* *Path B Minimum Cost:* **2 operations** (5->6, 7->8).
* *Let's check it:* Array becomes `[6, 8, 9]`. Product = 432. 432 / 4 = 108. It works! Cost = 2.

In this specific array, Path A (cost: 1) is better than Path B (cost: 2). So the answer is 1.

**But watch what happens if I change the array slightly to `[1, 5, 9]`:**

* **Path A (Solo):** * 1 to 4 -> **3 ops**
* 5 to 8 -> **3 ops**
* 9 to 12 -> **3 ops**
* *Path A Minimum Cost:* **3 operations**.


* **Path B (Teamwork):** * We have zero evens. We need two evens.
* Push 1 to 2 (**1 op**). Push 5 to 6 (**1 op**).
* *Path B Minimum Cost:* **2 operations**.


* *Let's check Path B:* Array is `[2, 6, 9]`. Product = `2 * 6 * 9 = 108`. 108 is perfectly divisible by 4.

If you only coded Path A, your program would spit out **3**, and Codeforces would instantly reject your submission because the true optimal answer is **2**.

Do you see how breaking a composite number down into its prime components completely changes the rules of the game?


### 2. The Math and States
For any single number x, how many operations does it take to reach the next multiple of k?If x % k == 0, it takes 0 operations.Otherwise, the distance to the next multiple is k - (x % k).For the special k = 4 trap, we just count how many even numbers (x % 2 == 0) already exist in the array.If we have $\ge$ 2 evens, the cost is 0.If we have 1 even, we just need to increment one odd number to make it even. Cost is 1.If we have 0 evens, we need to increment two odd numbers. Cost is 2.