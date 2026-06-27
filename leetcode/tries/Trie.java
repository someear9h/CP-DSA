package leetcode.tries;

import java.util.*;

class Node {
    Node[] links = new Node[26];
    boolean flag = false;

    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }

    public void put(char ch) {
        links[ch - 'a'] = new Node();
    }

    public Node get(char ch) {
        return links[ch - 'a'];
    }

    public void setEnd() {
        flag = true;
    }

    public boolean isEnd() {
        return flag;
    }
}

public class Trie {
    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for(char ch : word.toCharArray()) {
            if(!node.containsKey(ch)) {
                node.put(ch);
            }

            node = node.get(ch);
        }

        node.setEnd();
    }

    public boolean search(String word) {
        Node node = root;
        for(char ch : word.toCharArray()) {
            if(!node.containsKey(ch)) {
                return false;
            }

            node = node.get(ch);
        }

        return node.isEnd();
    }

    public boolean startsWith(String prefix) {
        Node node = root;
        for(char ch : prefix.toCharArray()) {
            if(!node.containsKey(ch)) {
                return false;
            }

            node = node.get(ch);
        }

        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        String[] operations = {"Trie", "insert", "search", "search", "startsWith", "insert", "search"};
        String[][] arguments = {{}, {"apple"}, {"apple"}, {"app"}, {"app"}, {"app"}, {"app"}};

        List<String> output = new ArrayList<>();

        for(int i = 0; i < operations.length; i++) {
            switch(operations[i]) {
                case "Trie" -> output.add("null");

                case "insert" -> {
                    trie.insert(arguments[i][0]);
                    output.add("null");
                }

                case "search" -> {
                    boolean exists = trie.search(arguments[i][0]);
                    output.add(exists ? "true" : "false");
                }

                case "startsWith" -> {
                    boolean exists = trie.startsWith(arguments[i][0]);
                    output.add(exists ? "true" : "false");
                }
            }
        }

        System.out.println(output);
    }
}
