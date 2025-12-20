import java.util.*;

// structure for the tree node
class Node {
    int data;
    Node left, right;

    public Node(int d) {
        data = d;
        left = right = null;
    }
}

public class TreeBoundaryTraversal {

    static class Solution {
        ArrayList<Integer> boundaryTraversal(Node root) {
            // if tree is empty, return empty list
            if(root == null) return new ArrayList<>();
            
            ArrayList<Integer> sama = new ArrayList<>();
            
            // 1. add root data first, but only if it is not a leaf
            // to avoid adding it twice (leaves are collected separately)
            if(!isLeaf(root)) sama.add(root.data);
            
            // 2. collect the left boundary (top to bottom)
            collectLeft(root.left, sama);
            
            // 3. collect all leaf nodes (left to right)
            collectLeaves(root, sama);
            
            // 4. collect the right boundary (bottom to top)
            collectRight(root.right, sama);
            
            return sama;
        }
        
        // helper to check if a node is a leaf (no children)
        boolean isLeaf(Node root) {
            return root.left == null && root.right == null;
        }
        
        // function to get left boundary
        // we exclude leaf nodes here to avoid duplicates
        void collectLeft(Node root, ArrayList<Integer> sama) {
            // base case: if null or leaf, stop. leaves are handled by collectleaves
            if(root == null || isLeaf(root)) return;
            
            // add data first (preorder style) because we want top-down order
            sama.add(root.data);
            
            // logic to stay on the boundary:
            // if left child exists, go left.
            // if left is missing but right exists, go right (because that is now the edge)
            if(root.left != null) collectLeft(root.left, sama);
            else if(root.right != null) collectLeft(root.right, sama);
        }
        
        // function to get right boundary
        // we need bottom-up order (reverse), so we use postorder style
        void collectRight(Node root, ArrayList<Integer> sama) {
            // base case: ignore nulls and leaves
            if(root == null || isLeaf(root)) return;
            
            // logic to stay on the boundary:
            // if right child exists, go right.
            // if right is missing but left exists, go left (that is the edge now)
            if(root.right != null) {
                collectRight(root.right, sama);
            } else if(root.left != null) {
                collectRight(root.left, sama);
            }
            
            // add data after recursion returns
            // this ensures the nodes are added in reverse (bottom to top)
            sama.add(root.data);
        }
        
        // standard dfs to find all leaves in order
        void collectLeaves(Node root, ArrayList<Integer> sama) {
            if(root == null) {
                return;
            }
            
            // if it is a leaf, add it to our list and stop
            if(isLeaf(root)) {
                sama.add(root.data);
                return;
            }
            
            // recurse left then right to maintain correct order
            collectLeaves(root.left, sama);
            collectLeaves(root.right, sama);
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
        ArrayList<Integer> result = sol.boundaryTraversal(root);

        // printing the result
        System.out.println(result);
    }
}