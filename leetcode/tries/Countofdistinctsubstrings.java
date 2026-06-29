package leetcode.tries;

class Node {
    public Node[] links = new Node[26];
    
    public boolean containsChar(char ch) {
        return links[ch - 'a'] != null;
    }
    
    public Node get(char ch) {
        return links[ch - 'a'];
    }
    
    public void put(char ch) {
        links[ch - 'a'] = new Node();
    }
}

class Trie {
    Node root;
    public Trie() {
        root = new Node();
    }
    
    public int insert(String word, int startIdx) {
        Node node = root;
        int cnt = 0;
        for(int i = startIdx; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(!node.containsChar(ch)) {
                node.put(ch);
                cnt++;
            }
            
            node = node.get(ch);
        }
        return cnt;
    }
}

public class Countofdistinctsubstrings {
    public static int countSubs(String s) {
        // code here
        int n = s.length();
        Trie trie = new Trie();
        int cnt = 0;
        
        for(int i = 0; i < n; i++) {
            cnt += trie.insert(s, i);
        }
        
        return cnt;
    }
}
