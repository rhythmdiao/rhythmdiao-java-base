import common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 * a1 → a2
 * ↘
 * c1 → c2 → c3
 * ↗
 * b1 → b2 → b3
 * begin to intersect at node c1.
 * Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */
public final class IntersectionofTwoLinkedLists_160 {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        Set<ListNode> set = new HashSet<ListNode>();
        while (nodeA != null) {
            set.add(nodeA);
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            if (set.contains(nodeB)) return nodeB;
            nodeB = nodeB.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode A = new ListNode(1);
        A.next = new ListNode(3);
        A.next.next = new ListNode(4);
        ListNode B = new ListNode(2);
        B.next = A.next;
        System.out.println(IntersectionofTwoLinkedLists_160.getIntersectionNode(A, B));
    }
}
