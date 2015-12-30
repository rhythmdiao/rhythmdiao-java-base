import java.util.Arrays;

/**
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public final class KthLargestElementinanArray_215 {
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int length = nums.length;
        int result = 0;
        int count = 0;
        if (length == k) return nums[0];
        for (int i = length - 1; i >= 1; i--) {
            if (nums[i] >= nums[i - 1]) {
                if ((++count) == k) {
                    result = nums[i];
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(KthLargestElementinanArray_215.findKthLargest(new int[]{2, 1}, 2));
    }
}
