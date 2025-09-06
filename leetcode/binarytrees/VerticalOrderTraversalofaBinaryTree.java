import java.util.*;

class Pair<U, V> {
    public U first;
    public V second;
    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
    public U getKey() { return first; }
    public V getValue() { return second; }
}

public class VerticalOrderTraversalofaBinaryTree {

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

    private static List<List<Integer>> verticalTraversal(TreeNode root) {
        Queue<Pair<TreeNode, Pair<Integer, Integer>>> q = new LinkedList<>();
        Map<Integer, Map<Integer, PriorityQueue<Integer>>> mp = new TreeMap<>();

        q.add(new Pair<>(root, new Pair<>(0, 0)));

        while(!q.isEmpty()) {
            Pair<TreeNode, Pair<Integer, Integer>> p = q.remove();
            TreeNode curr = p.getKey();
            int x = p.getValue().getKey();
            int y = p.getValue().getValue();

            mp.computeIfAbsent(x, k -> new TreeMap<>())
              .computeIfAbsent(y, k -> new PriorityQueue<>())
              .add(curr.val);

            if(curr.left != null) {
                q.add(new Pair<>(curr.left, new Pair<>(x - 1, y + 1)));
            }

            if(curr.right != null) {
                q.add(new Pair<>(curr.right, new Pair<>(x + 1, y + 1)));
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        
        for(Map<Integer, PriorityQueue<Integer>> ys : mp.values()) {
            List<Integer> cols = new ArrayList<>();

            for(PriorityQueue<Integer> pq : ys.values()) {
                while(!pq.isEmpty()) {
                    cols.add(pq.poll());
                }
            }

            res.add(cols);
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

        System.out.println(verticalTraversal(root));
        System.out.println("Root: " + root.val);
    }
}
