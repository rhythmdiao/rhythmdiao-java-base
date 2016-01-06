/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */
public final class JumpGame_55 {
    public static boolean canJump(int[] nums) {
        int length = nums.length;
        if (length <= 1) return true;
        int reached = 0;
        for (int i = 0; i <= reached; i++) {
            if (nums[i] + i > reached) reached = nums[i] + i;
            if (reached >= length - 1) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(JumpGame_55.canJump(new int[]{3,0,8,2,0,0,1}));
    }
}
