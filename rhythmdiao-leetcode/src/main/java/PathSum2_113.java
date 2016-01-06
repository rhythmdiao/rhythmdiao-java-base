import common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
public final class PathSum2_113 {
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if (root == null) return result;
        Stack<Integer> stack = new Stack<Integer>();
        find(root, sum, result, stack);
        return result;
    }

    private static void find(TreeNode node, int sum, List<List<Integer>> result, Stack<Integer> stack) {
        if (node == null) return;
        stack.add(node.val);
        if (node.left == null && node.right == null && node.val == sum) {
            List<Integer> list = new LinkedList<Integer>(stack);
            result.add(list);
        }
        find(node.left, sum - node.val, result, stack);
        find(node.right, sum - node.val, result, stack);
        stack.pop();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        System.out.println(PathSum2_113.pathSum(root, 22));
    }
}
