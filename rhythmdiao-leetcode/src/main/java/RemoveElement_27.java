import java.util.Arrays;

/**
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */
public final class RemoveElement_27 {
    public static int removeElement(int[] nums, int val) {
        int length = nums.length;
        if (length == 0) return length;
        Arrays.sort(nums);
        if (nums[length - 1] < val) return length;
        int start = 0;
        int end = length - 1;
        for (; start < length; start++) {
            if (nums[start] == val) break;
        }
        for (; end > 0; end--) {
            if (nums[end] == val) break;
        }
        return length - (end - start) -1;
    }

    public static void main(String[] args) {
        System.out.println(RemoveElement_27.removeElement(new int[]{4,5}, 4));
    }
}
