```java
// author: someear1h

public class Solution {

    static int extractAndCountDigits(int n) {
        int cnt = 0;
        while(n > 0) {
            int lastDigit = n % 10;
            cnt++;
            n /= 10;
        }

        return cnt;
    }

    public static void main(String[] args) {
        int n1 = 7789;
        int n2 = 6743784;

        System.out.println(extractAndCountDigits(n1));
        System.out.println(extractAndCountDigits(n2));
    }
}
```

## TC and SC

tc: O(log to the base 10 (N)) -> O(log10 * N)
whenever we divide something by a digit say x and loop
the tc has log to the base x always

sc: O(1)