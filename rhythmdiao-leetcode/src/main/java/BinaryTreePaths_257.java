import common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * For example, given the following binary tree:
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * All root-to-leaf paths are:
 * ["1->2->5", "1->3"]
 */
public final class BinaryTreePaths_257 {
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new LinkedList<String>();
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        String path = "";
        if (root != null) {
            nodeStack.push(root);
            findPaths(list, path, root, nodeStack);
        }
        return list;
    }

    private static void findPaths(List<String> list, String path, TreeNode node, Stack<TreeNode> nodeStack) {
        path += nodeStack.peek().val;
        if (node.left == null && node.right == null) {
            nodeStack.pop();
            list.add(path);
            return;
        }
        if (node.left != null) {
            path += path.endsWith("->") ? "" : "->";
            nodeStack.push(node.left);
            findPaths(list, path, node.left, nodeStack);
        }
        if (node.right != null) {
            path += path.endsWith("->") ? "" : "->";
            nodeStack.push(node.right);
            findPaths(list, path, node.right, nodeStack);
        }
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        System.out.println(BinaryTreePaths_257.binaryTreePaths(head));
    }
}
