import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once.
 * For example:
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 */
public final class SingleNumber3_260 {
    public static int[] singleNumber(int[] nums) {
        int length = nums.length;
        if (length <= 2) return nums;
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

        int[] result = new int[set.size()];
        Iterator<Integer> it = set.iterator();
        result[0] = it.next();
        result[1] = it.next();
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(SingleNumber3_260.singleNumber(new int[]{-1, 0})));
    }
}
