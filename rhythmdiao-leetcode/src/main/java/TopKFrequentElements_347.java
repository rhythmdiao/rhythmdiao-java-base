import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public final class TopKFrequentElements_347 {
    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new LinkedList<Integer>();
        Map<Integer, Item> map = new HashMap<Integer, Item>();
        for (int item : nums) {
            if (map.containsKey(item)) {
                map.get(item).frequency += 1;
            } else {
                Item i = new Item();
                i.number = item;
                i.frequency = 1;
                map.put(item, i);
            }
        }

        List<Item> values = new LinkedList<Item>();
        for (Map.Entry entry : map.entrySet()) {
            values.add((Item) entry.getValue());
        }
        Collections.sort(values);

        for (int i = 0; i < k; i++) {
            result.add(values.get(i).number);
        }
        return result;
    }

    static class Item implements Comparable {
        int number;
        int frequency;

        @Override
        public int compareTo(Object o) {
            Item that = (Item) o;
            if (this.frequency == that.frequency) return 0;
            if (this.frequency > that.frequency) return -1;
            return 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(TopKFrequentElements_347.topKFrequent(new int[]{1, 3, 3, 2, 2, 3}, 2));
    }
}

