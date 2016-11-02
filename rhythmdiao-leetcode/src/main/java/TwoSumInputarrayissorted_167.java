import java.util.Arrays;

/**
 * Given an array of integers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2.
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution.
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 */
public final class TwoSumInputarrayissorted_167 {
    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];

        int length = numbers.length;

        int pos;
        for (int i = 0; i < length; i++) {
            pos = Arrays.binarySearch(numbers, target - numbers[i]);
            if (pos > 0 && i < pos) {
                result[0] = i + 1;
                result[1] = pos + 1;
                break;
            } else if (pos > 0 && i == pos && i < length + 1) {
                if (numbers[i] == numbers[i + 1]) {
                    result[0] = i + 1;
                    result[1] = i + 2;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] numbers = {3, 24, 50, 79, 88, 150, 345};
        int taret = 200;
        System.out.println(twoSum(numbers, taret));
    }
}
