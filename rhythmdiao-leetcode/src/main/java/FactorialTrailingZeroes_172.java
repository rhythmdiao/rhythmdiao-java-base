/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note: Your solution should be in logarithmic time complexity.
 */
public final class FactorialTrailingZeroes_172 {
    public static int trailingZeroes(int n) {
        int result = 0;
        final int division = 5;
        while (n > 0) {
            result += n / division;
            n /= division;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(FactorialTrailingZeroes_172.trailingZeroes(15));
    }
}
