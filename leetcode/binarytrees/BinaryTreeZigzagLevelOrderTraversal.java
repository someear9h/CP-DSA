import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {

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

    private static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if(root == null) return res;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean ltor = true;

        while(!q.isEmpty()) {
            int lvlSize = q.size();
            List<Integer> lvl = new ArrayList<>();
            
            for(int i = 0; i < lvlSize; i++) {
                TreeNode curr = q.remove();
                if(ltor) {
                    lvl.add(curr.val);
                } else {
                    lvl.add(0, curr.val);
                }

                if(curr.left != null) {
                    q.add(curr.left);
                }

                if(curr.right != null) {
                    q.add(curr.right);
                }
            }
            ltor = !ltor; // flip the flag
            res.add(lvl);
        }

        return res;
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

        System.out.println(zigzagLevelOrder(root));
        System.out.println("Root: " + root.val);
    }
}
