package leetcode.tries;

class Node {
    public Node[] links = new Node[26];
    public boolean end = false;
    
    public boolean containsChar(char ch) {
        return links[ch - 'a'] != null;
    }
    
    public Node get(char ch) {
        return links[ch - 'a'];
    }
    
    public void put(char ch) {
        links[ch - 'a'] = new Node();
    }
    
    public boolean isEnd() {
        return end;
    }
    
    public void setEnd() {
        end = true;
    }
}

class Trie {
    Node root;
    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        Node node = root;
        for(char ch : word.toCharArray()) {
            if(!node.containsChar(ch)) {
                node.put(ch);
            }
            
            node = node.get(ch);
        }
        
        node.setEnd();
    }
    
    public boolean allPrefixExist(String word) {
        Node node = root;
        for(char ch : word.toCharArray()) {
            node = node.get(ch);
            if(node == null || !node.isEnd()) {
                return false;
            }
        }
        
        return true;
    }
}

public class LongestValidWordwithAllPrefixes {
    public String longestValidWord(String[] words) {
        // code here
        Trie trie = new Trie();
        
        int n = words.length;
        for(String word : words) {
            trie.insert(word);
        }
        
        String longest = "";
        for(int i = 0; i < n; i++) {
            String word = words[i];
            
            if(trie.allPrefixExist(word)) {
                if(word.length() > longest.length()) {
                    longest = word;
                } 
                else if(word.length() == longest.length() && word.compareTo(longest) < 0) {
                    longest = word;
                }
            }
        }
        
        return longest;
    }
}