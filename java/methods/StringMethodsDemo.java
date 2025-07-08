package methods;

public class StringMethodsDemo {
    public static void main(String[] args) {
        String s = " Hello, Samarth123! ";

        System.out.println(s.length());              // 19
        System.out.println(s.charAt(1));             // 'H'
        System.out.println(s.substring(1, 6));       // Hello
        System.out.println(s.toLowerCase());         //  hello, samarth123!
        System.out.println(s.toUpperCase());         //  HELLO, SAMARTH123!
        System.out.println(s.trim());                // "Hello, Samarth123!"
        System.out.println(s.startsWith(" "));       // true
        System.out.println(s.endsWith("! "));        // false
        System.out.println(s.contains("Samarth"));   // true
        System.out.println(s.indexOf("a"));          // 9
        System.out.println(s.replace("123", "456")); //  Hello, Samarth456!
        System.out.println(s.isEmpty());             // false
        System.out.println(s.equals("Hello"));       // false
        System.out.println(s.equalsIgnoreCase(" hello, samarth123! ")); // true
        System.out.println(s.trim().split(",")[0]);  // Hello
    }
}
/*
 * | Method               | Description                      |
| -------------------- | -------------------------------- |
| `length()`           | Number of characters             |
| `charAt(index)`      | Character at position            |
| `substring(i, j)`    | Substring from `i` to `j-1`      |
| `toLowerCase()`      | Convert to lowercase             |
| `toUpperCase()`      | Convert to uppercase             |
| `trim()`             | Removes leading/trailing spaces  |
| `startsWith(prefix)` | Checks start                     |
| `endsWith(suffix)`   | Checks end                       |
| `contains(str)`      | Checks if substring exists       |
| `indexOf(char)`      | First occurrence index           |
| `replace(a, b)`      | Replaces all `a` with `b`        |
| `isEmpty()`          | Checks if string is empty        |
| `equals(str)`        | Case-sensitive comparison        |
| `equalsIgnoreCase()` | Case-insensitive comparison      |
| `split(delimiter)`   | Splits string based on delimiter |

 */
