import common.ListNode;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 */
public final class PartitionList_86 {
    public static ListNode partition(ListNode head, int x) {
        ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(0);
        ListNode result = node1;
        ListNode partition = node2;
        while (head != null) {
            if (head.val >= x) {
                node2.next = new ListNode(head.val);
                node2 = node2.next;
            } else {
                node1.next = new ListNode(head.val);
                node1 = node1.next;
            }
            head = head.next;
        }
        while (partition != null){
            node1.next = partition.next;
            node1 = node1.next;
            partition = partition.next;
        }
        return result.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        System.out.println(PartitionList_86.partition(head, 3));
    }
}
