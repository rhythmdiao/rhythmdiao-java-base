import java.util.LinkedList;
import java.util.List;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5,
 * Return
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public final class PascalsTriangle_118 {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if (numRows == 0) return result;
        List<Integer> item = new LinkedList<Integer>();
        item.add(1);
        result.add(item);
        if (numRows == 1) return result;
        while (--numRows > 0) {
            List<Integer> newItem = new LinkedList<Integer>();
            newItem.add(1);
            if (item.size() > 1) {
                for (int i = 1; i < item.size(); i++) {
                    newItem.add(item.get(i) + item.get(i - 1));
                }
            }
            newItem.add(1);
            result.add(newItem);
            item = newItem;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(PascalsTriangle_118.generate(5));
    }
}
