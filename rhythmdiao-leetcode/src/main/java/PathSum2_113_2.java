import common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * return
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 */
public final class PathSum2_113_2 {
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        return find(root, sum, result, queue);
    }

    private static List<List<Integer>> find(TreeNode node, int sum, List<List<Integer>> result, Queue<TreeNode> queue) {
        if (node != null) {
            if (node.left == null && node.right == null && node.val == sum) {
                List<Integer> list = new LinkedList<Integer>();
                while (!queue.isEmpty()) {
                    list.add(queue.poll().val);
                }
                result.add(list);
            }

            if (node.left != null) {
                Queue<TreeNode> queueLeft = new LinkedList<TreeNode>(queue);
                queueLeft.add(node.left);
                find(node.left, sum - node.val, result, queueLeft);
            }

            if (node.right != null) {
                Queue<TreeNode> queueRight = new LinkedList<TreeNode>(queue);
                queueRight.add(node.right);
                find(node.right, sum - node.val, result, queueRight);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        System.out.println(PathSum2_113_2.pathSum(root, 22));
    }
}
