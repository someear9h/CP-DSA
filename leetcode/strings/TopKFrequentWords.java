import java.util.*;

// tc: O(N + MlogM) where N is no of words and M is no of unique words
// sc: O(M + K) for hashmaps and pq to store unique elements and k for the list
public class TopKFrequentWords {
    // make a Pair class for String and its frequency
    static class Pair {
        String first;
        int second;

        Pair(String f, int s) {
            first = f;
            second = s;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {

        // make priority queue sorted as most frequent words at top and if frequency matches
        // try lexographically 
        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> b.second == a.second ? a.first.compareTo(b.first) : b.second - a.second);
        
        // map to store String and their no of occurences
        Map<String, Integer> mp = new HashMap<>();

        for(String s : words) {
            mp.put(s, mp.getOrDefault(s, 0) + 1);
        }

        // add the strings and frequency in pq
        for(Map.Entry<String, Integer> entry : mp.entrySet()) {
            pq.add(new Pair(entry.getKey(), entry.getValue()));
        }

        List<String> ans = new ArrayList<>();

        // take the first top k frequent words and add it in the 'ans' list
        while(k > 0 && !pq.isEmpty()) {
            Pair curr = pq.remove();
            ans.add(curr.first);
            k--;
        }

        return ans;
    }
}
