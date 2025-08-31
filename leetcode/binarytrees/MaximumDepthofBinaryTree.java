public class MaximumDepthofBinaryTree {

    static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

        // TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static int maxDepth(TreeNode root) {
        if(root == null) return 0;

        int leftHt = maxDepth(root.left);
        int rightHt = maxDepth(root.right);

        int height = Math.max(leftHt, rightHt) + 1;

        return height;
    }

    public static void main(String[] args) {
        // Construct a tree
        //         1
        //        / \
        //       2   3
        //      / \   \
        //     4   5   6

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        root.right = new TreeNode(3, null, new TreeNode(6));

        int res = maxDepth(root);
        System.out.println(res + " root value: " + root.val);
    }
}
