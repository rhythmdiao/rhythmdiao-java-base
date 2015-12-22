import java.util.Arrays;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
public final class MissingNumber_268 {
    public static int missingNumber(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        int result = 0;
        if (nums[0] != 0) return result;
        result = nums[0];
        for (int i = 1; i < length; i++) {
            if (nums[i] == result + 1) {
                result = nums[i];
            } else {
                result = nums[i - 1];
                break;
            }
        }
        return result + 1;
    }

    public static void main(String[] args) {
        System.out.println(MissingNumber_268.missingNumber(new int[]{1, 2}));
    }
}
