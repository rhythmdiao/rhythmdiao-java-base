import java.util.Arrays;

/**
 * Given an array nums,
 * write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public final class MoveZeros_283 {
    public static int[] moveZeroes(int[] nums) {
        int pos = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] != 0) {
                nums[pos] = nums[i];
                pos++;
            }
        }
        for (; pos < length; pos++) {
            nums[pos] = 0;
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.print(Arrays.toString(MoveZeros_283.moveZeroes(new int[]{0, 1, 0, 3, 12, 0})));
    }
}
