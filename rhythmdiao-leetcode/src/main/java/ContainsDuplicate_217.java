import java.util.Arrays;

/**
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 */
public final class ContainsDuplicate_217 {
    public static boolean containsDuplicate(int[] nums) {
        if (nums.length <= 1) return false;
        int length = nums.length;
        Arrays.sort(nums);
        int num = nums[0];
        for (int i = 1; i < length; i++) {
            if (num == nums[i]) {
                return true;
            }
            num = nums[i];
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(ContainsDuplicate_217.containsDuplicate(new int[]{1, 3, 2, 1}));
    }
}
