import java.util.Arrays;

/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public final class SingleNumber2_137 {
    public static int singleNumber(int[] nums) {
        int length = nums.length;
        if (nums.length == 1) return nums[0];
        Arrays.sort(nums);
        int current = nums[0];
        int count = 0;
        for (int i = 1; i < length; i++) {
            if (current == nums[i]) {
                current = nums[i];
                count++;
            } else {
                if (count < 2) {
                    break;
                } else {
                    current = nums[i];
                    count = 0;
                }
            }
        }
        return current;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 2, 3}));
    }
}
