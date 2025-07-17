import java.io.*;
import java.util.*;

class Pair {
    String first;
    int second;

    Pair(String f, int s) {
        this.first = f;
        this.second = s;
    }
}

public class MinimumGeneticMutation {
    private static int minMutation(String startGene, String endGene, String[] bank) {
        Queue<Pair> q = new LinkedList<>();
        Set<String> st = new HashSet<>(Arrays.asList(bank));

        q.add(new Pair(startGene, 0));
        st.remove(startGene);
        
        while(!q.isEmpty()) {
            Pair curr = q.remove();
            String gene = curr.first;
            int count = curr.second;

            if(gene.equals(endGene)) {
                return count;
            }

            String geneLetters = "ACGT";

            // go through every character in gene
            for(int i = 0; i < gene.length(); i++) {
                for(int change = 0; change < geneLetters.length(); change++) {
                    char[] geneChar = gene.toCharArray();
                    geneChar[i] = geneLetters.charAt(change);

                    String newGene = new String(geneChar);

                    if(st.contains(newGene)) {
                        st.remove(newGene);
                        q.add(new Pair(newGene, count + 1));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        String startGene = "AACCGGTT", endGene = "AAACGGTA";
        String[] bank = {"AACCGGTA","AACCGCTA","AAACGGTA"};

        System.out.println(minMutation(startGene, endGene, bank));
    }
}
