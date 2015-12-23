import java.util.*;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 */
public final class MajorityElement2_229 {
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new LinkedList<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        int limit = (nums.length / 3) + 1;
        for (Map.Entry<Integer, Integer> set : map.entrySet()) {
            if (set.getValue() >= limit) {
                list.add(set.getKey());
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(MajorityElement2_229.majorityElement(new int[]{1, 2, 3}));
    }
}
