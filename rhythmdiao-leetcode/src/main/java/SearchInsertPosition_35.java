/**
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 */
public final class SearchInsertPosition_35 {
    public static int searchInsert(int[] nums, int target) {
        int pos = 0;
        int i = 0;
        if (nums == null) return pos;
        int length = nums.length;
        for (; i < length; i++) {
            if (target <= nums[i]) {
                pos = i;
                break;
            }
        }
        return pos == 0 ? i : pos;
    }

    public static void main(String[] args) {
        System.out.println(SearchInsertPosition_35.searchInsert(new int[]{1}, 2));
    }
}
