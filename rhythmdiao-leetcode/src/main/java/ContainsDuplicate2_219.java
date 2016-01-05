import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers and an integer k,
 * find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
 */
public final class ContainsDuplicate2_219 {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int length = nums.length;
        if (length <= 1) return false;
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(nums[i], list);
            }
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            if (list.size() >= 2) {
                int pointer = list.get(0);
                for (int i = 1; i < list.size(); i++) {
                    if (list.get(i) - pointer <= k) return true;
                    pointer = list.get(i);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(ContainsDuplicate2_219.containsNearbyDuplicate(new int[]{1, 3, 2, 4, 1}, 4));
    }
}
