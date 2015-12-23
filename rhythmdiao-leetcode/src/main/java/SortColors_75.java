import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * <p/>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's,
 * then 1's and followed by 2's.
 * Could you come up with an one-pass algorithm using only constant space?
 */
public final class SortColors_75 {
    public static int[] sortColors(int[] nums) {
        int redNums = 0, whiteNums = 0, blueNums = 0;
        for (int num : nums) {
            if (num == 0) redNums++;
            if (num == 1) whiteNums++;
            if (num == 2) blueNums++;
        }
        int i = 0;
        for (; i < redNums; i++) {
            nums[i] = 0;
        }
        for (; i < redNums + whiteNums; i++) {
            nums[i] = 1;
        }
        for (; i < redNums + whiteNums + blueNums; i++) {
            nums[i] = 2;
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(SortColors_75.sortColors(new int[]{1, 0})));
    }
}
