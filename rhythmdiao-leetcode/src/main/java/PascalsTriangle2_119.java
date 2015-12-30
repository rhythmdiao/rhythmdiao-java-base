import java.util.LinkedList;
import java.util.List;

/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public final class PascalsTriangle2_119 {
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> list = new LinkedList<Integer>();
        list.add(1);
        if (rowIndex == 0) return list;
        while (rowIndex > 0) {
            List<Integer> item = new LinkedList<Integer>();
            item.add(1);
            if (list.size() > 1) {
                for (int i = 1; i < list.size(); i++) {
                    item.add(list.get(i) + list.get(i - 1));
                }
            }
            item.add(1);
            list = item;
            rowIndex--;
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(PascalsTriangle2_119.getRow(5));
    }
}
