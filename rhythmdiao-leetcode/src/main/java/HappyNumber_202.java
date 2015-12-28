import java.util.HashSet;

/**
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the squares of its digits,
 * and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers.
 * Example: 19 is a happy number
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public final class HappyNumber_202 {
    public static boolean isHappy(int n) {
        int result;
        HashSet<Integer> resultSet = new HashSet<Integer>();
        result = calculate(n, 0);
        resultSet.add(result);
        while (result != 1) {
            int temp = calculate(result, 0);
            if (resultSet.contains(temp)) return false;
            result = temp;
            resultSet.add(result);
        }
        return true;
    }

    private static int calculate(int n, int result) {
        while (n > 0) {
            int d = n % 10;
            result += d * d;
            n = n / 10;
            calculate(n, result);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(HappyNumber_202.isHappy(2));
    }
}
