import java.util.*;
class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}

public class BottomViewofBinaryTree {
    static class Solution {

        static class Pair {
            Node f;
            int s; // x  axis values
            
            Pair(Node _f, int _s) {
                f = _f;
                s = _s;
            }
        }
        
        public ArrayList<Integer> bottomView(Node root) {
            if(root == null) return new ArrayList<>();
            
            TreeMap<Integer, Integer> mp = new TreeMap<>();
            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(root, 0)); // node, x
            
            while(!q.isEmpty()) {
                Pair p = q.remove();
                Node node = p.f;
                int x = p.s;
                
                mp.put(x, node.data);
                
                if(node.left != null) 
                    q.add(new Pair(node.left, x-1));
                    
                if(node.right != null) 
                    q.add(new Pair(node.right, x+1));
            }
            
            return new ArrayList<>(mp.values());
        }
    }

    public static void main(String[] args) {
        // constructing the tree
        //         1
        //       /   \
        //      2     3
        //     / \   / \
        //    4   5 6   7
        //       / \
        //      8   9
        
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.right.left = new Node(8);
        root.left.right.right = new Node(9);

        Solution sol = new Solution();
        ArrayList<Integer> result = sol.bottomView(root);

        // printing the result
        System.out.println(result);
    }
}