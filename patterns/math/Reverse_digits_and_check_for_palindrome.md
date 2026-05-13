## for reversing the digits

```java
// author: someear1h

public class Solution {

    static public int reverseDigits(int n) { 
        // with no leading zeroes
        int revN = 0;
        
        while(n > 0) {
            int lastDig = n % 10;
            revN = (revN * 10) + lastDig;
            n /= 10;
        }
        
        return revN;
    }

    public static void main(String[] args) {
        int n1 = 12345;
        int n2 = 200;

        System.out.println(reverseDigits(n1));
        System.out.println(reverseDigits(n2));
    }
}
```

## TC and SC



## for checking if number's is palindrome

```java
// author: someear1h

public class Solution {

    static boolean isPaindrome(int n) { 
        int temp = n;
        int revN = 0;
        
        while(n > 0) {
            int lastDig = n % 10;
            revN = (revN * 10) + lastDig;
            n /= 10;
        }
        
        return revN == temp;
    }

    public static void main(String[] args) {
        int n1 = 12345;
        int n2 = 1221;

        System.out.println(isPaindrome(n1));
        System.out.println(isPaindrome(n2));
    }
}
```

## TC and SC