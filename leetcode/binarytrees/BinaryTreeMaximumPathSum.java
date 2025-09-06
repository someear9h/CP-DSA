public class BinaryTreeMaximumPathSum {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        // TreeNode() {}
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static int res = (int)-1e9;
    
    private static int maxPathSum(TreeNode root) {
        dfs(root);

        return res;
    }

    private static int dfs(TreeNode root) {
        if(root == null) return 0;

        int leftSum = Math.max(0, dfs(root.left)); // 0 for ignoring -ve values
        int rightSum = Math.max(0, dfs(root.right));

        res = Math.max(res, leftSum + rightSum + root.val);

        return Math.max(leftSum, rightSum) + root.val;
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

        System.out.println(maxPathSum(root));
        System.out.println("Root: " + root.val);
    }
}
