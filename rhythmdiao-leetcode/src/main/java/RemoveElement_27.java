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
        if (length == 1 && nums[0] == val) return 0;
        if (nums[length - 1] < val) return length;
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] != val) nums[count++] = nums[i];
            else nums[i] = 0;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(RemoveElement_27.removeElement(new int[]{3,3}, 3));
    }
}
