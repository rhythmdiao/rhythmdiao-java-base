import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */
public final class SearchforaRange_34 {
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        int length = nums.length;
        if (length == 0) return result;
        if (target > nums[length - 1]) return result;
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            if (nums[i] == target) {
                list.add(i);
            }
        }
        if (list.size() == 0) return result;
        result[0] = list.get(0);
        result[1] = list.get(list.size() - 1);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(SearchforaRange_34.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }
}
