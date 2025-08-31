public class DiameterofBinaryTree {
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

    private static int dia = 0;
    private static int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return dia;
    }

    private static int height(TreeNode root) {
        if(root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        dia = Math.max(dia, left + right);

        return 1 + Math.max(left, right);
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


        System.out.println(diameterOfBinaryTree(root));
        System.out.println("Root: " + root.val);
    }
}
