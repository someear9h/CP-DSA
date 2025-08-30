import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversal {
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

    private static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);

        return res;
    }

    private static void postorder(TreeNode root, List<Integer> res) {
        // left -> right -> root
        if(root == null) return;

        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }

    private static List<Integer> postorderTraversalUsingStack(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();

        if(root == null) return postorder;

        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();

        st1.push(root);

        while(!st1.isEmpty()) {
            root = st1.pop();
            st2.push(root);

            if(root.left != null) st1.push(root.left);
            if(root.right != null) st1.push(root.right);
        }

        while(!st2.isEmpty()) {
            postorder.add(st2.pop().val);
        }

        return postorder;
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
        List<Integer> result = postorderTraversal(root);
        List<Integer> res = postorderTraversalUsingStack(root);

        // Print result
        System.out.println(result);
        System.out.println(res);
    }
}
