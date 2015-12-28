import common.ListNode;

/**
 * Reverse a singly linked list.
 * Hint:
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList_206 {
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode reversed = head;
        ListNode node = head.next;
        head.next = null;
        while (node != null) {
            ListNode temp = node.next;
            node.next = reversed;
            reversed = node;
            node = temp;
        }
        return reversed;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        System.out.println(ReverseLinkedList_206.reverseList(head));
    }
}
