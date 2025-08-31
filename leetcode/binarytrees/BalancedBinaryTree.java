public class BalancedBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private static int getHeight(TreeNode root) {
        if(root == null) return 0;

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        if(left == - 1 || right == -1) return -1;

        if(Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
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


        System.out.println(isBalanced(root));
        System.out.println("Root: " + root.val);
    }
}
