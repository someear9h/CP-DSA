public class LongestUnivaluePath {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

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

    static int mx = 0;
    private static int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return mx > 0? mx - 1 : 0;
    }

    private static int dfs(TreeNode root) {
        if(root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        int leftSide = 0, rightSide = 0;

        if(root.left != null && root.left.val == root.val) {
            leftSide = left;
        }

        if(root.right != null && root.right.val == root.val) {
            rightSide = right;
        }

        mx = Math.max(mx, leftSide + rightSide + 1);
        return Math.max(leftSide, rightSide) + 1;
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

        System.out.println(longestUnivaluePath(root));
        System.out.println("Root: " + root.val);
    }
}
