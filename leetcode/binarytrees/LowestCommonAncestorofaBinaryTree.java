import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class LowestCommonAncestorofaBinaryTree {
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

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> qu = new LinkedList<>();

        parent.put(root, null); // no parent for root
        qu.add(root); 

        while(!qu.isEmpty()) {
            TreeNode curr = qu.remove();

            if(curr.left != null) {
                parent.put(curr.left, curr);
                qu.add(curr.left);
            }

            if(curr.right != null) {
                parent.put(curr.right, curr);
                qu.add(curr.right);
            }
        }

        List<TreeNode> anse = new ArrayList<>();
        while(p != null) {
            anse.add(p);
            p = parent.get(p);
        }

        while(!anse.contains(q)) {
            q = parent.get(q);
        }

        return q;
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

    // Choose nodes p and q (must be references to the actual nodes in the tree)
    TreeNode p = root.left;          // Node with value 2
    TreeNode q = root.right.right;   // Node with value 6

    TreeNode lca = lowestCommonAncestor(root, p, q);

    System.out.println("LCA of " + p.val + " and " + q.val + " = " + lca.val);
    }
}
