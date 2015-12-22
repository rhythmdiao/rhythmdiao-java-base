import common.ListNode;

/**
 * Given a linked list, determine if it has a cycle in it.
 * Follow up:
 * Can you solve it without using extra space?
 */
public final class LinkedListCycle_141 {
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode node1 = head;
        ListNode node2 = head;
        while (node1 != null && node1.next != null) {
            node1 = node1.next.next;
            node2 = node2.next;
            if (node1 == node2) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = node;
        System.out.println(LinkedListCycle_141.hasCycle(node));
    }
}
