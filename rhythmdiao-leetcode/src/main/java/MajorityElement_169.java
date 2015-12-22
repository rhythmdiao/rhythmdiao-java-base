import java.util.Arrays;

/**
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
public final class MajorityElement_169 {
    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int result = nums[0];
        int length = nums.length;
        int half = length / 2;
        for (; half < length; half += half) {
            if (result == nums[half]) break;
            result = nums[half];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(MajorityElement_169.majorityElement(new int[]{2, 1, 1}));
    }
}
