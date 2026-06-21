Here is the exact template for your Pattern Library. This pattern is one of the most powerful tools in competitive programming. Once you see it, $O(N^2)$ overlapping interval problems instantly become trivial $O(N)$ sweeps.

## The Universal Line Sweep / Difference Array Template

Every problem in this pattern follows this exact flow:

```
STEP 1: Reduce the range to "Boundary Events"
        Transform an operation on a whole subarray [l...r] 
        into exactly TWO $O(1)$ events: a "Start" and an "End".
        
        e.g. Add 1 to range [l..r]: 
        diff[l] += 1 (The effect starts here)
        diff[r + 1] -= 1 (The effect stops here)

STEP 2: Sweep the line to resolve the true state
        Walk through the array from left to right, maintaining a 
        running total (prefix sum) of the boundary events.
        
        Ask: "What does the running total represent right now?" 
        (e.g., number of overlapping lights, total passengers in a car, 
        current altitude).

STEP 3: Process the resolved state
        Now that you have the true state mapped out in $O(N)$, 
        apply your logic (Greedy, DP, or simple counting) to find the answer.
        
        Ask: "Now that I know exactly where the overlaps/gaps are, 
        what is the greedy/optimal move?"

STEP 4: Prove the Time Complexity limits
        Ask: "If I did this with a nested loop, would it TLE?"
        If N = 10^5 and ranges can be 10^5 long, nested loops take 10^10 operations.
        Difference array takes 10^5 + 10^5 = 2 * 10^5 operations. Safe. ✅

```

**That's it. Every problem. No exceptions.**

---

## Watch Me Apply This To The Minimum Lights Problem

```
STEP 1: diff[start] += 1 (light begins)
        diff[end + 1] -= 1 (light ends)
        This maps out all overlapping bulb coverage in $O(N)$ time.

STEP 2: Sweep left-to-right. 
        currCov += diff[i]
        If currCov > 0, it means we are inside a lit zone (isLit[i] = true).

STEP 3: Process the resolved state (Greedy placement).
        Scan isLit[i]. If we find a dark spot (isLit[i] == false):
        Place bulb at i + 1 (covers i, i+1, i+2).
        Jump the pointer: i += 2.

STEP 4: Complexity check.
        Original overlapping ranges could cause $O(N^2)$. 
        Line sweep maps it in $O(N)$. Greedy scan takes $O(N)$. 
        Total $O(N)$. Optimal and safe. ✅

```

---

## The Decision Tree

```
After identifying an overlapping ranges/intervals problem:

Q1: What are the coordinate bounds?
    
    Coordinates <= 10^6   → Use a fixed-size integer array `int[] diff = new int[n+1]`
    Coordinates > 10^6    → Use a TreeMap<Integer, Integer> to store only the exact 
                            event coordinates (sorts automatically).


Q2: What is the problem asking for?
    
    Maximum Overlaps      → Track the `max(running_total)` during the sweep.
    Check Capacity limits → If `running_total > limit` at any point, return false.
    Find the Gaps         → If `running_total == 0`, you found empty space.
    Apply final values    → The running total IS the final array value.


Q3: Do intervals have different "weights"?
    
    YES (e.g. +3 passengers) → diff[start] += weight, diff[end + 1] -= weight
    NO (e.g. just 1 light)   → diff[start] += 1, diff[end + 1] -= 1

```

---

## The Classic Problems Mapped To This Template

```
LC 370  Range Addition         add val to [l..r] → diff[l]+=v, diff[r+1]-=v, sweep
LC 1094 Car Pooling            passengers overlap? → diff[l]+=p, diff[r]-=p, max <= cap
LC 253  Meeting Rooms II       max simultaneous meetings → map events, track max overlap
LC 2772 Apply Ops to Make 0    subtract k-length range to 0 → diff array + greedy
LC 1893 Check if All Ints Cov. check if [left, right] is covered → diff sweep, check 0s

```

---

## Watch Me Apply To 3 Leetcode Problems

### LC 370: Range Addition

```
You have an array of 0s. You get queries [start, end, val].
Add `val` to all elements from `start` to `end`. Return final array.

STEP 1: diff[start] += val
        diff[end + 1] -= val
STEP 2: Sweep the line. running_total += diff[i].
        array[i] = running_total.
STEP 3: No extra processing needed, the sweep IS the answer.
STEP 4: $O(N)$ instead of $O(N * Q)$ nested loops. ✅

```

### LC 1094: Car Pooling

```
You have a car with `capacity`. Trips are [num_passengers, start, end].
Can you complete all trips without exceeding capacity?

STEP 1: diff[start] += num_passengers (people get in)
        diff[end] -= num_passengers (people get out, NO +1 because they leave AT 'end')
STEP 2: Sweep the line from location 0 to 1000.
        current_passengers += diff[i]
STEP 3: Process state: if current_passengers > capacity at any point, return false.
        If sweep finishes, return true.
STEP 4: Maps overlapping passenger loads in $O(N)$ time. ✅

```

### LC 253: Meeting Rooms II

```
Given meeting intervals [start, end], find minimum conference rooms required.
(This is identical to asking "what is the maximum number of overlapping meetings?")

STEP 1: Because times can be large, use a TreeMap instead of an array.
        map.put(start, map.getOrDefault(start, 0) + 1)
        map.put(end, map.getOrDefault(end, 0) - 1)
STEP 2: Sweep through the sorted keys of the map.
        current_rooms += map.get(time)
STEP 3: Process state: max_rooms_needed = Math.max(max_rooms_needed, current_rooms)
STEP 4: Resolves overlap clusters efficiently in $O(N \log N)$ (due to TreeMap sorting). ✅

```

---

## The Muscle Memory Drill

Every time you see a problem modifying or checking overlapping subarrays/ranges, **immediately write these on paper:**

```
start_event = ???       (what happens at index L?)

end_event = ???         (what happens at index R + 1?)

running_total = ???     (what physical thing does my prefix sum represent?)

condition/max = ???     (what am I checking during or after the sweep?)

```

Do this for 5 problems and you will never write a `for(int j = start; j <= end)` inner loop ever again.

---

## The Trigger Signals Cheatsheet

```
SIGNAL IN PROBLEM                             → PATTERN
"add/subtract a value to subarray [l...r]"    → Difference Array
"maximum overlapping intervals/meetings"      → Line Sweep (track max)
"do all these intervals cover the target?"    → Line Sweep (find gaps of 0)
"capacity limit on overlapping tasks"         → Line Sweep (check > limit)
"N = 10^5 and Q queries of range updates"     → Difference Array

```

---

## Sister Problems To Solidify This Pattern

| Problem | What the Diff represents | Post-Sweep Action |
| --- | --- | --- |
| LC 370 Range Addition | value added to element | return the array |
| LC 1094 Car Pooling | passengers in the car | check if > capacity |
| LC 253 Meeting Rooms II | active meetings | track maximum overlapping |
| LC 1893 Check Covered | 1 if covered, 0 if not | check if target range has any 0s |
| LC 732 My Calendar III | active bookings | track maximum overlapping |
| **Minimum Lights** | lit by a bulb | greedily place bulbs on 0s |

Master these 6 and you will instantly conquer any Range Update / Overlap problem.