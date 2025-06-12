// https://takeuforward.org/plus/dsa/problems/find-square-root-of-a-number

#include <iostream>
using namespace std;

class Solution {
public:
    long long floorSqrt(long long n)  {
      int left = 1, right = n;
      long long ans = 0;
      while(left <= right) {
        int mid = left + (right - left) / 2;
        if(mid * mid <= (long long) n) {
            ans = mid;
            left = mid + 1;
        } else {
            right = mid - 1;
        }
      }

      return ans;
    }
};

int main() {
    Solution sol;
    int n = 36;
    cout << sol.floorSqrt(n) << endl;

    return 0;
}