import java.util.Arrays;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * For example,
 * Given input array nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 */
public final class RemoveDuplicatesfromSortedArray_26 {
    public static int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 1) return length;
        Arrays.sort(nums);
        int temp = nums[0];
        int count = 1;
        for (int i = 1; i < length; i++) {
            if (temp != nums[i]) {
                nums[count++] = nums[i];
                temp = nums[i];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(RemoveDuplicatesfromSortedArray_26.removeDuplicates(new int[]{1, 1, 2}));
    }
}
