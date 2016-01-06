import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public final class ValidParentheses_20 {
    public static boolean isValid(String s) {
        int length = s.length();
        if (length == 0) return true;
        if (length % 2 != 0) return false;
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '{' || c == '[' || c == '(') {
                stack.add(c);
            } else {
                if (!stack.isEmpty() && map.get(c) == stack.peek()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(ValidParentheses_20.isValid("(("));
    }
}
