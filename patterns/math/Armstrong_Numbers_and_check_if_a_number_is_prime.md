
## Armstrong number

```java
class Solution {
    static boolean armstrongNumber(int n) {
        int N = n;
        int sum = 0;
        // get the last digit -> cube it -> add to the sum
        while(n > 0) {
            int lastDig = n % 10;
            sum += Math.pow(lastDig, 3);
            n = n/10;
        }
        
        return sum == N;
    }
}
```

## Check if a number is Prime or not
```java
class Solution {
    static boolean isPrime(int n) {
        int cnt = 0;
        for(int i=1; i *i <= n; i++) {
            if(n % i == 0) {
                cnt++;
                if(i != n/i) cnt++;
            }
        }
        
        return cnt == 2 ? true : false;
    }
}
```

tc: O(sqrt(N))
sc: O(1)