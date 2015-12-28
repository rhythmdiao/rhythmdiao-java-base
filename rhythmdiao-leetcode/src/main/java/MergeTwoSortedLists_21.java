import common.ListNode;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public final class MergeTwoSortedLists_21 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode node = new ListNode(0);
        ListNode pointer = node;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                pointer.next = l2;
                l2 = l2.next;
            } else {
                pointer.next = l1;
                l1 = l1.next;
            }
            pointer = pointer.next;
        }

        if (l1 == null) pointer.next = l2;
        if (l2 == null) pointer.next = l1;

        return node.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(1);
        System.out.println(MergeTwoSortedLists_21.mergeTwoLists(l1, l2));
    }
}
