package methods;

public class CharacterMethodsDemo {
    public static void main(String[] args) {
        char ch1 = 'A';
        char ch2 = 'a';
        char ch3 = '1';
        char ch4 = ' ';
        char ch5 = '@';

        System.out.println(Character.isLetter(ch1));      // true
        System.out.println(Character.isDigit(ch3));       // true
        System.out.println(Character.isLetterOrDigit(ch5)); // false
        System.out.println(Character.isWhitespace(ch4));  // true
        System.out.println(Character.isUpperCase(ch1));   // true
        System.out.println(Character.isLowerCase(ch2));   // true

        System.out.println(Character.toUpperCase(ch2));   // 'A'
        System.out.println(Character.toLowerCase(ch1));   // 'a'
    }
}
/*
 * | Method                | Description                       |
| --------------------- | --------------------------------- |
| `isLetter(ch)`        | Checks if `ch` is a letter        |
| `isDigit(ch)`         | Checks if `ch` is a digit         |
| `isLetterOrDigit(ch)` | Checks if `ch` is letter or digit |
| `isWhitespace(ch)`    | Checks if `ch` is a whitespace    |
| `isUpperCase(ch)`     | Checks if uppercase               |
| `isLowerCase(ch)`     | Checks if lowercase               |
| `toUpperCase(ch)`     | Converts to uppercase             |
| `toLowerCase(ch)`     | Converts to lowercase             |

 */
