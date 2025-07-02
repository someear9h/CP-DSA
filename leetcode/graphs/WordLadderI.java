import java.io.IOException;
import java.util.*;

class Pair {
    String first;
    int second;

    Pair(String first, int second) {
        this.first = first;
        this.second = second;
    }
}

public class WordLadderI {
    private static int solve(String beginWord, String endWord, List<String> wordList) {
        // make a queue and hashset
        Queue<Pair> q = new LinkedList<>();
        Set<String> st = new HashSet<>();

        // add the first word in queue
        q.add(new Pair(beginWord, 1));

        // add all the words in wordlist in set for easy removals
        for(String s : wordList) {
            st.add(s);
        }

        // remove the starting word as we have processed it from the set
        st.remove(beginWord);
        
        while(!q.isEmpty()) {
            String word = q.peek().first;
            int steps = q.peek().second;
            q.poll();

            if(word.equals(endWord) == true) return steps;

            // now go through each word
            for(int i = 0; i < word.length(); i++) {
                // change each char in word from a to z
                for(char ch = 'a'; ch <= 'z'; ch++) {
                    char[] replacedCharArray = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);

                    // if the replaced word already is in set then remove it and to the queue
                    if(st.contains(replacedWord)) {
                        st.remove(replacedWord);
                        q.add(new Pair(replacedWord, steps + 1));
                    }
                }
            }
        }
        return 0; 
    } 

    public static void main(String[] args) throws IOException {
        String startWord = "der", targetWord = "dfs";
        String[] wordList = {
            "des",
            "der",
            "dfr",
            "dgt",
            "dfs"
        };

        List<String> list = Arrays.asList(wordList);

        
        int ans = solve(startWord, targetWord, list);

        System.out.print(ans);

        System.out.println();
    }
}
