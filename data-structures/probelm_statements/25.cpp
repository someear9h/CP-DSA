#include <iostream>
#include <vector>
using namespace std;

int solve(vector<int> &workload) {
    int currStreak = 0;
    int maxDays = 0;

    for(int i = 0; i < workload.size(); i++) {
        if(workload[i] > 6) {
            currStreak++;
        } else {
            maxDays = max(maxDays, currStreak);
            currStreak = 0;
        }
    }

    maxDays = max(maxDays, currStreak);
    return maxDays;
}

int main() {
    vector<int> arr = {2, 3, 7, 8, 7, 6, 3, 8, 12, 11, 12, 10}; 

    int ans = solve(arr);
    cout << ans << endl;

    return 0;
}

/*
You are an IT company&#39;s manager. Based on their performance over the last N working days,
you must rate your employee. You are given an array of N integers called workload, where
workload[i] represents the number of hours an employee worked on an i th day. The employee
must be evaluated using the following criteria:
 Rating = the maximum number of consecutive working days when the employee has
worked more than 6 hours.
You are given an integer N where N represents the number of working days. You are given an
integer array workload where workload[i] represents the number of hours an employee worked
on an i th day.
Task - Determine the employee rating.
 N = 12
 workload = [2, 3, 7, 8, 7, 6, 3, 8, 12, 11, 12, 10]
Approach - Workload with consecutive hours &gt; 6 = [2, 3, 7, 8, 7, 6, 3, 8, 12, 11, 12, 10] =&gt; 
Longest Interval =  [8,12,11,12,10]
Therefore return 5.
*/