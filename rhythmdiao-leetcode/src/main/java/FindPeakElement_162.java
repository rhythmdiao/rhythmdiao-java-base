/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that num[-1] = num[n] = -∞.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * click to show spoilers.
 * Note:
 * Your solution should be in logarithmic complexity.
 */
public final class FindPeakElement_162 {
    public static int findPeakElement(int[] nums) {
        int length = nums.length;
        int index = 0;
        if (length <= 1) return index;
        for (; index < length - 1; index++) {
            if (nums[index] > nums[index + 1]) break;
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(FindPeakElement_162.findPeakElement(new int[]{1, 2, 3, 1, 2}));
    }
}
