/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * Some hints:
 * Could negative integers be palindromes? (ie, -1)
 * If you are thinking of converting the integer to string, note the restriction of using extra space.
 * You could also try reversing an integer.
 * However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow.
 * How would you handle such case?
 * There is a more generic way of solving this problem.
 */
public final class PalindromeNumber_9 {
    public static boolean isPalindrome(int x) {
        return x >= 0 && x == reverse(x, 0);
    }

    private static int reverse(int x, int result) {
        while (x > 0) {
            result = result * 10 + x % 10;
            x = x / 10;
            reverse(x, result);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(PalindromeNumber_9.isPalindrome(12321));
    }
}
