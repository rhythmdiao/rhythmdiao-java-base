/**
 * Write a function that takes a string as input and returns the string reversed.
 * Example:
 * Given s = "hello", return "olleh".
 */

public final class ReverseString_344 {
    public static String reverseString(String s) {
        int length = s.length();
        if (length < 2) return s;
        StringBuilder result = new StringBuilder();
        char[] c = s.toCharArray();
        for (int i = c.length - 1; i >= 0; i--) {
            result.append(c[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(ReverseString_344.reverseString("hello world"));
    }
}
