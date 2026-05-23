### 1. The "GOD" Intuition: The Laws of Exponents

The brute force way to calculate $2^{13}$ is to multiply 2 by itself 13 times: $2 \cdot 2 \cdot 2 \dots$ This takes $O(N)$ time. If $N$ is 1 billion, your code will time out.

This algorithm works because it exploits two fundamental rules of math to take massive "shortcuts":

* **Rule 1: Power of a Power (The Even Shortcut)**

$$x^{a \cdot b} = (x^a)^b$$



If your exponent is even, you can cut the work exactly in half. For example, $2^{12}$ is the exact same thing as $(2^2)^6$, which is $4^6$. Instead of multiplying a number 12 times, you just squared the base once, and now you only have to multiply 6 times!
* **Rule 2: Product of Powers (The Odd Fix)**

$$x^{a+b} = x^a \cdot x^b$$



If your exponent is odd, you can't divide it cleanly by 2. So, you just "pull out" one copy of the base to make the remaining exponent even. For example, $2^{13} = 2^1 \cdot 2^{12}$. Now that you have $2^{12}$, you can use the Even Shortcut.

---

### 2. Tracing the Image (The "Why")

Let's look at the exact example in your image: `power(2, 13)`

1. **n = 13 (Odd):** We can't halve 13. So we pull out one `2`.
* Math: $2^{13} = 2 \cdot 2^{12}$
* Code: `2 * power(2, 12)`


2. **n = 12 (Even):** Now we can take a massive shortcut! We square the base ($2 \cdot 2 = 4$) and halve the exponent ($12 / 2 = 6$).
* Math: $2^{12} = (2^2)^6 = 4^6$
* Code: `power(4, 6)`


3. **n = 6 (Even):** Take another shortcut! Square the base ($4 \cdot 4 = 16$), halve the exponent ($6 / 2 = 3$).
* Math: $4^6 = (4^2)^3 = 16^3$
* Code: `power(16, 3)`


4. **n = 3 (Odd):** Can't halve 3. Pull out one `16`.
* Math: $16^3 = 16 \cdot 16^2$
* Code: `16 * power(16, 2)`


5. **n = 2 (Even):** Shortcut time! Square the base ($16 \cdot 16 = 256$), halve the exponent ($2 / 2 = 1$).
* Math: $16^2 = (16^2)^1 = 256^1$
* Code: `power(256, 1)`


6. **n = 1 (Base Case):** Any number to the power of 1 is itself. Return `256`.

Now, the recursive calls bubble back up, multiplying the pieces we "pulled out" during the odd steps: `256 * 16 * 2 = 8192`.

---

### 3. Why This is Grandmaster Level

By halving the exponent at every even step, the number of operations shrinks drastically. This is called **Binary Exponentiation**.

Instead of doing $N$ multiplications, you only do $\log_2(N)$ multiplications.
If $N = 1,000,000$, a brute force loop takes 1,000,000 steps. This algorithm takes about **20 steps**. That is why this pattern is essential.

Here is an interactive tool so you can visualize this exponent reduction live.

While the image shows the *recursive* approach, the most elite way to write this in a competitive programming contest is actually *iterative* (using a while loop) to avoid stack overflow errors on massive test cases. 


## CODE: 
```java
class Solution {
    public double myPow(double x, int n) {
        long exp = n;
        double ans = 1.0;
        
        if(exp == 0) return 1;

        if(exp < 0) {
            exp = exp * -1;
        }

        while(exp > 0) {
            // odd
            if(exp % 2 != 0) {
                ans *= x;
                exp -= 1;
            }

            else {
                x = x * x;
                exp /= 2;
            }
        }   

        if(n < 0) {
            return 1.0 /ans;
        }

        return ans;
    }
}
```