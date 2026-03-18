/**
* someear1h 
*/

/**
Welcome back to the dojo. 

You have stepped into Phase 2 of your Number Theory training: **Factorization & Greedy Splitting**. 

I am not going to give you the code, and I am not going to give you the formula. You are going to build the engine yourself. To do that, we need to break this down logically. 

Grab a piece of paper, and let's tear this problem apart.

### Hint 1: The Greedy Choice
The problem requires you to find three *distinct* integers $a, b, c \ge 2$ such that $a \times b \times c = n$.

Imagine $n = 64$. You need to pick your first number, $a$. 
To maximize your chances of having a large enough number left over to split into $b$ and $c$ without repeating yourself, should you pick the **smallest** possible factor for $a$, or the **largest** possible factor? 

### Hint 2: The Chain Reaction
If you figure out how to pick the absolute best $a$, the game instantly gets easier. 
Once you have $a$, you can shrink the problem. You now have a leftover value, let's call it $n_{rem}$, where $n_{rem} = n / a$.

Now the problem is just: Find $b$ and $c$ such that $b \times c = n_{rem}$.
How would you find $b$? You would use the exact same logic you used to find $a$! You just find the smallest valid factor of $n_{rem}$.

### Hint 3: The Rules of the Matrix (Edge Cases)
If you find $a$, and then you find $b$, you don't even need to search for $c$. 
$c$ is mathematically forced to be $n_{rem} / b$. 

But the problem has strict rules: **Distinct integers.**
If your algorithm blindly picks the smallest factors, what are the three conditions you must check to ensure your $c$ is actually a valid answer?
*(Think about how $c$ compares to $a$, $b$, and the minimum allowed value).*

***

**Your turn.** Don't write any C++ code yet. Reply to me with your answers to these two questions:
1. What is the $O(\sqrt{N})$ strategy to find the absolute best $a$ and $b$?
2. What are the exact logical checks (`if` statements) you must run on $c$ at the very end to guarantee the answer is `YES`?
*/

#include <bits/stdc++.h>
using namespace std;

using ll = long long;

#define all(x) (x).begin(), (x).end()
#define eb emplace_back

void solve() {
    int n;
    cin >> n;

    int fact = 2;
    int cnt = 0;
    unordered_set<int> seto;

    while(fact*fact <= n) {
        if(n % fact == 0) {
            cnt++;
            seto.insert(fact);
            n /= fact;
            if(cnt == 2) break;
        }
        fact++;
    }

    seto.insert(n);
    if(seto.size() < 3) cout << "NO" << "\n";
    else {
        cout << "YES" << "\n";
        for(auto& i : seto) {
            cout << i << " ";
        }
        cout << "\n";
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t = 1;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}