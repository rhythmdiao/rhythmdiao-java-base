import common.TreeNode;

/**
 * Created by mayuxing on 2015/12/18.
 */
public final class MaximumDepthofBinaryTree_104 {
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        System.out.print(MaximumDepthofBinaryTree_104.maxDepth(new TreeNode(14)));
    }
}
