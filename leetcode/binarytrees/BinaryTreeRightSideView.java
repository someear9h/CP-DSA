import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTreeRightSideView {
    static class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> sama = new ArrayList<>();
            if(root == null) return sama;

            dfs(root, sama, 0);

            return sama;
        }

        private void dfs(TreeNode node, List<Integer> sama, int lvl) {
            if(node == null) return;

            if(lvl == sama.size()) {
                sama.add(node.val);
            }

            dfs(node.right, sama, lvl+1);
            dfs(node.left, sama, lvl+1);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);

        Solution sol = new Solution();

        System.out.println(sol.rightSideView(root));
    }
}
