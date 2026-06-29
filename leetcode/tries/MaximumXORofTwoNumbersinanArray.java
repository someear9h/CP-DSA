package leetcode.tries;

class Node {
    Node[] links = new Node[2]; // 0 and 1

    public boolean containsBit(int bit) {
        return links[bit] != null;
    }

    public void put(int bit) {
        links[bit] = new Node();
    }

    public Node get(int bit) {
        return links[bit];
    }

}

class Trie {
    Node root;
    public Trie() {
        root = new Node();
    }

    public void insert(int num) {
        Node node = root;
        for(int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if(!node.containsBit(bit)) {
                node.put(bit);
            }
            node = node.get(bit);
        }
    }

    public int getMax(int num) {
        Node node = root;
        int maxXor = 0;
        for(int i =31; i>= 0; i--) {
            int bit = (num >> i) & 1;
            if(node.containsBit(1-bit)) {
                maxXor = maxXor | (1 << i);
                node = node.get(1-bit);
            } else {
                node = node.get(bit);
            }
        }

        return maxXor;
    }
}

public class MaximumXORofTwoNumbersinanArray {
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            trie.insert(nums[i]);
        }
        
        int res = 0;
        for(int i = 0; i < n; i++) {
            res = Math.max(res, trie.getMax(nums[i]));
        }

        return res;
    }
}