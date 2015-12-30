import common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * For example:
 * Given the following binary tree,
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 * You should return [1, 3, 4].
 */
public final class BinaryTreeRightSideView_199 {
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        if (root == null) return list;
        Queue<TreeNode> looksLike = new LinkedList<TreeNode>();
        looksLike.add(root);
        //add null as a tag for tree's depth
        looksLike.add(null);
        int val = root.val;
        while (!looksLike.isEmpty()) {
            TreeNode node = looksLike.poll();
            if (node != null) {
                val = node.val;
                if (node.left != null) looksLike.add(node.left);
                if (node.right != null) looksLike.add(node.right);
            } else {
                list.add(val);
                if (looksLike.isEmpty()) break;
                looksLike.add(null);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        System.out.println(BinaryTreeRightSideView_199.rightSideView(root));
    }
}
