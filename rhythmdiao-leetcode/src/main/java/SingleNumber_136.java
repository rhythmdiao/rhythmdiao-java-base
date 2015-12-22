import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
public final class SingleNumber_136 {
    public static int singleNumber(int[] nums) {
        if (nums == null) return 0;
        int length = nums.length;
        if (length == 1) return nums[0];
        Set<Integer> set = new HashSet<Integer>();
        Arrays.sort(nums);
        set.add(nums[0]);
        for (int i = 1; i < length; i++) {
            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        return set.iterator().next();
    }

    public static void main(String[] args) {
        System.out.println(SingleNumber_136.singleNumber(new int[]{2, 1, 2, 1, 3}));
    }
}
