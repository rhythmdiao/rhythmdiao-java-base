import common.ListNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example,
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 */
public final class RemoveNthNodeFromEndofList_19 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        Queue<ListNode> queue = new LinkedList<ListNode>();
        ListNode result = new ListNode(0);
        while (head != null) {
            queue.add(head);
            head = head.next;
        }
        ListNode node = result;
        int size = queue.size();
        while (size > 0) {
            if (n == size) {
                queue.poll();
                if (n == 1) {
                    node.next = null;
                }
            } else {
                node.next = queue.poll();
                node = node.next;
            }
            size--;
        }
        return result.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.print(RemoveNthNodeFromEndofList_19.removeNthFromEnd(head, 1));
    }
}
