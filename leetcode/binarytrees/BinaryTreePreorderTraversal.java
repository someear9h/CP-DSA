import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal {
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

    private static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);

        return res;
    }

    private static void preorder(TreeNode root, List<Integer> res) {
        if(root == null) return;

        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
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

        // Call preorderTraversal
        List<Integer> result = preorderTraversal(root);

        // Print result
        System.out.println("Preorder Traversal: " + result);
    }
}
