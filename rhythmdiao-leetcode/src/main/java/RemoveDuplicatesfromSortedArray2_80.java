/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3.
 * It doesn't matter what you leave beyond the new length.
 */
public final class RemoveDuplicatesfromSortedArray2_80 {
    public static int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 2) return length;
        int count = 1;
        int temp = nums[0];
        boolean hasTwo = false;
        for (int i = 1; i < length; i++) {
            int num = nums[i];
            if (num == temp) {
                if (!hasTwo) {
                    nums[count++] = num;
                    hasTwo = true;
                }
            } else {
                hasTwo = false;
                nums[count++] = num;
                temp = num;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(RemoveDuplicatesfromSortedArray2_80.removeDuplicates(new int[]{1, 1, 1, 2}));
    }
}
