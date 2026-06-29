package leetcode.tries;

import java.util.Arrays;

class Node {
    Node[] links = new Node[2];

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

    public int getMaxXor(int x) {
        Node node = root;
        int maxXOR = 0;

        for(int i = 31; i >= 0; i--) {
            int bit = (x >> i) & 1;
            if(node.containsBit(1-bit)) {
                maxXOR |= (1 << i);
                node = node.get(1-bit);
            } else {
                node = node.get(bit);
            }
        }

        return maxXOR;
    }
}

public class MaximumXORWithanElementFromArray {
    public static int[] maximizeXor(int[] nums, int[][] queries) {
        int n = nums.length;
        int q = queries.length;
        int[] res = new int[q];

        Trie trie = new Trie();

        int[][] offQ = new int[q][3]; // x, m, original index
        for(int i = 0; i < q; i++) {
            offQ[i][0] = queries[i][0];
            offQ[i][1] = queries[i][1];
            offQ[i][2] = i;
        }

        Arrays.sort(nums);
        Arrays.sort(offQ, (a,b) -> Integer.compare(a[1], b[1]));

        int idx = 0; // index for nums array
        for(int i = 0; i < q; i++) {
            int origIdx =offQ[i][2];

            while(idx < n && nums[idx] <= offQ[i][1]) {
                trie.insert(nums[idx]);
                idx++;
            }

            if(idx == 0) {
                res[origIdx] = -1;
            } else {
                res[origIdx] = trie.getMaxXor(offQ[i][0]);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,3,4};
        int[][] queries = {{3, 1}, {1, 3}, {5, 6}};

        int[] res = maximizeXor(nums, queries);
        
        for(int num : res) {
            System.out.print(num + " ");
        }

        System.out.println();
    }
}