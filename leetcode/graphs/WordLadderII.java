import java.util.*;

public class WordLadderII {
    private static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        for(String s : wordList) set.add(s);

        Queue<List<String>> q = new LinkedList<>();
        List<String> ls = new ArrayList<>();
        ls.add(beginWord);
        q.add(ls);

        List<String> usedOnLevel = new ArrayList<>();
        usedOnLevel.add(beginWord);
        int level = 0;

        List<List<String>> ans = new ArrayList<>();

        while(!q.isEmpty()) {
            List<String> curr = q.remove();

            if(curr.size() > level) {
                level++;
                for(String it : usedOnLevel) {
                    set.remove(it);
                }
            }

            String word = curr.get(curr.size() - 1);

            if(word.equals(endWord)) {
                if(ans.size() == 0) {
                    ans.add(curr);
                } else if(ans.get(0).size() == curr.size()) {
                    ans.add(curr);
                }
            }

            for(int i = 0; i < word.length(); i++) {
                for(char c = 'a'; c <= 'z'; c++) {
                    char[] replacedCharArray = word.toCharArray();
                    replacedCharArray[i] = c;

                    String changedWord = new String(replacedCharArray);
                    if(set.contains(changedWord)) {
                        curr.add(changedWord);

                        List<String> temp = new ArrayList<>(curr);
                        q.add(temp);
                        usedOnLevel.add(changedWord);
                        curr.remove(curr.size() - 1);
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // --- Example 1: Classic "hit" to "cog" ---
        System.out.println("--- Example 1: Finding ladders from 'hit' to 'cog' ---");
        String beginWord1 = "hit";
        String endWord1 = "cog";
        // The dictionary of allowed words.
        List<String> wordList1 = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));

        // Call the method to find all the shortest transformation sequences.
        List<List<String>> ladders1 = findLadders(beginWord1, endWord1, wordList1);

        // Print the results for Example 1.
        if (ladders1.isEmpty()) {
            System.out.println("No transformation sequence found.");
        } else {
            System.out.println("Found " + ladders1.size() + " shortest transformation sequences:");
            for (List<String> ladder : ladders1) {
                // The String.join method is a clean way to print the list with arrows.
                System.out.println(String.join(" -> ", ladder));
            }
        }
        System.out.println(); // Add a newline for better separation.


        // --- Example 2: No possible solution ---
        System.out.println("--- Example 2: Finding ladders from 'a' to 'c' ---");
        String beginWord2 = "a";
        String endWord2 = "c";
        List<String> wordList2 = new ArrayList<>(Arrays.asList("a", "b", "c"));

        // Call the method. In this case, it should return an empty list.
        List<List<String>> ladders2 = findLadders(beginWord2, endWord2, wordList2);

        // Print the results for Example 2.
        if (ladders2.isEmpty()) {
            System.out.println("No transformation sequence found.");
        } else {
            System.out.println("Found " + ladders2.size() + " shortest transformation sequences:");
            for (List<String> ladder : ladders2) {
                System.out.println(String.join(" -> ", ladder));
            }
        }
    }
}
