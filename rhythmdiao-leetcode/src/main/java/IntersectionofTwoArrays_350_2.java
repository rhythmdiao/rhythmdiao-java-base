import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given two arrays, write a function to compute their intersection.
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to num2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public final class IntersectionofTwoArrays_350_2 {
    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new LinkedList<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int item : nums1) {
            if (!map.containsKey(item)) {
                map.put(item, 1);
            } else {
                map.put(item, map.get(item) + 1);
            }
        }
        for (int item : nums2) {
            if (map.containsKey(item)) {
                if (map.get(item) > 0) {
                    list.add(item);
                    map.put(item, map.get(item) - 1);
                }
            }
        }
        int[] result = new int[list.size()];
        int i = 0;
        for (int item : list) {
            result[i++] = item;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(IntersectionofTwoArrays_350_2.intersect(new int[]{1}, new int[]{2, 2}));
    }
}
