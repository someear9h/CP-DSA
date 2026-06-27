package leetcode.tries;

import java.util.*;

class Node {
    Node[] links = new Node[26];
    int endCnt = 0;
    int prefixCnt = 0;

    boolean containsChar(char ch) {
        return links[ch - 'a'] != null;
    }

    void put(char ch) {
        links[ch - 'a'] = new Node();
    }

    Node get(char ch) {
        return links[ch - 'a'];
    }

    boolean isEnd() {
        return endCnt > 0;
    }

    void setEnd() {
        endCnt++;
    }

    void increasePrefixCnt() {
        prefixCnt++;
    }

    void decPrefixCnt() {
        prefixCnt--;
    }

    void incEndCnt() {
        endCnt++;
    }

    void decEndCnt() {
        endCnt--;
    }
}

public class TrieII {
    Node root;

    TrieII() {
        root = new Node();
    }

    void insert(String word) {
        Node node = root;
        for(char ch : word.toCharArray()) {
            if(!node.containsChar(ch)) {
                node.put(ch);
            }

            node = node.get(ch);
            node.increasePrefixCnt();
        }

        node.incEndCnt();
    }

    int countWordsEqualTo(String word) {
        Node node = root;
        for(char ch : word.toCharArray()) {
            if(!node.containsChar(ch)) return 0;

            node = node.get(ch);
        }

        return node.endCnt;
    }

    int countWordsStartingWith(String prefix) {
        Node node = root;
        for(char ch : prefix.toCharArray()) {
            if(!node.containsChar(ch)) return 0;
            node = node.get(ch);
        }

        return node.prefixCnt;
    }

    void erase(String word) {
        Node node = root;
        for(char ch : word.toCharArray()) {
            if(!node.containsChar(ch)) return;

            node = node.get(ch);
            node.decPrefixCnt();
        }

        node.decEndCnt();
    }

    public static void main(String[] args) {
        String[] methods = {"Trie", "insert", "countWordsEqualTo", "insert", 
        "countWordsStartingWith", "erase", "countWordsStartingWith"};

        String[][] values = {{}, {"apple"}, {"apple"}, {"app"}, {"app"}, {"apple"}, {"app"}};

        TrieII trie2 = new TrieII();

        List<String> res = new ArrayList<>();

        for(int i = 0; i < values.length; i++) {
            switch(methods[i]) {
                case "Trie" -> res.add("null");

                case "insert" -> {
                    trie2.insert(values[i][0]);
                    res.add("null");
                }

                case "countWordsEqualTo" -> {
                    int cnt = trie2.countWordsEqualTo(values[i][0]);
                    res.add(String.valueOf(cnt));
                }

                case "countWordsStartingWith" -> {
                    int cnt = trie2.countWordsStartingWith(values[i][0]);
                    res.add(String.valueOf(cnt));
                }

                case "erase" -> {
                    trie2.erase(values[i][0]);
                    res.add("null");
                }
            }
        }

        System.out.println(res);
    }
}
