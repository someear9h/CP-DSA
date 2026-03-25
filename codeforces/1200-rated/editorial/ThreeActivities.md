### The Grandmaster Intuition: The "Top 3" Principle

When a problem asks you to pick a small, fixed number of items (like exactly 3) from massive arrays ($N = 10^5$), you should immediately stop thinking about DP and start thinking about **Greedy / Sorting**.

If Monocarp wants to maximize his friends, he obviously wants to pick the day with the absolute highest number for each activity. 
But what if the best day for Skiing is Day 1, and the best day for Movies is *also* Day 1? He can't do both on Day 1. He needs a backup plan.

**How many backups do we need?**
* Worst case scenario: The absolute best day for Skiing, Movies, and Games are ALL the exact same day. 
* Even worse: The second-best day for all three is ALSO the exact same day!
* Therefore, to guarantee we can resolve any scheduling conflicts without missing out on the best possible combinations, we only ever need to look at the **Top 3 days for each activity**.

If we extract the Top 3 days for Skiing, the Top 3 for Movies, and the Top 3 for Games, we only have $3 \times 3 \times 3 = 27$ possible combinations. We just check all 27, throw away the ones where the days overlap, and find the maximum!

### Complexity Analysis
* **Time Complexity:** `O(N log N)` because of the three `sort()` operations. The nested loops at the end run in strict `O(1)` time (exactly 27 iterations), so they add zero overhead. This easily passes the 2.0 second limit for $N = 10^5$.
* **Space Complexity:** `O(N)` to store the arrays of pairs. 