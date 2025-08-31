import java.util.PriorityQueue;

public class KthLargestPerfectSubtreeSizeinBinaryTree {
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

    private static PriorityQueue<Integer> pq;

    private static int dfs(TreeNode root, int k) {
        if(root == null) return 0; 

        int left = dfs(root.left, k);
        int right = dfs(root.right, k);

        if((root.left != null && root.right == null) || 
            (root.left == null && root.right != null)) {
            return -1;
        }

        if(left == -1 || right == -1 || left != right) {
            return -1;
        }

        if(pq.size() < k) {
            pq.add(left + right + 1);
        } else {
            if(pq.peek() < left + right + 1) {
                pq.remove();
                pq.add(left + right + 1);
            }
        }

        return left + right + 1; // return this for the above TreeNodes
    }

    private static int kthLargestPerfectSubtree(TreeNode root, int k) {
        pq = new PriorityQueue<>();
        dfs(root, k);

        if(pq.size() < k) return -1;

        return pq.peek();
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

        int k = 2;
        System.out.println(kthLargestPerfectSubtree(root, k));
        System.out.println("Root: " + root.val);
    }
}
