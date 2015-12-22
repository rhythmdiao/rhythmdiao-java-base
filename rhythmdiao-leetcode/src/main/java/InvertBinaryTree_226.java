import common.TreeNode;

/**
 * Invert a binary tree.
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * to
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 */
public final class InvertBinaryTree_226 {
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode tempNode;
        tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public static void main(String[] args) {
        System.out.println(InvertBinaryTree_226.invertTree(new TreeNode(1)));
    }
}
