import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MaximizeAlternatingSumUsingSwaps {
    private static class DSU {
        int[] parent;
        int[] size;

        public DSU(int n) {
            parent = new int[n];
            size = new int[n];

            for(int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int findUPar(int node) {
            if(node == parent[node]) return node;

            int ulp = findUPar(parent[node]);
            return parent[node] = ulp;
        }

        private void unionSize(int u, int v) {
            int up = findUPar(u);
            int vp = findUPar(v);

            if(up == vp) return;

            if(size[up] < size[vp]) {
                parent[up] = vp;
                size[vp] = size[vp] + size[up];
            } else {
                parent[vp] = up;
                size[up] += size[vp];
            }
        }
    }

    public long maxAlternatingSum(int[] nums, int[][] swaps) {
        int n = nums.length;
        DSU dsu = new DSU(n);

        // now unionise the indices in swap
        for(int[] s : swaps) {
            dsu.unionSize(s[0], s[1]);
        }

        // make 1 hashmap for storing ultimate parents and their indices
        // make 1 hashmap for storing ultimate parents and their values
        Map<Integer, List<Integer>> compIndices = new HashMap<>();
        Map<Integer, List<Integer>> compValues = new HashMap<>();

        // go through nums and then fill the map
        for(int i = 0; i < n; i++) {
            // get ultimate parent of i
            int ulp = dsu.findUPar(i);

            compIndices.computeIfAbsent(ulp, k -> new ArrayList<>()).add(i);
            compValues.computeIfAbsent(ulp, k -> new ArrayList<>()).add(nums[i]);
        }

        long totSum = 0;

        for(int ulp : compValues.keySet()) {
            List<Integer> indices = compIndices.get(ulp);
            List<Integer> values = compValues.get(ulp);

            int evenCnt = 0;
            for(int it : indices) {
                if(it % 2 == 0) {
                    evenCnt++;
                }
            }

            // sort the values list in descending order to get the largest values in nums
            values.sort(Collections.reverseOrder());

            // add the largest number for all number of even indices
            for(int i = 0; i < evenCnt; i++) {
                totSum += values.get(i);
            }

            for(int i = evenCnt; i < values.size(); i++) {
                totSum -= values.get(i);
            }
        }

        return totSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read size of array
        int n = sc.nextInt();

        // Read array elements
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // Read number of swaps
        int m = sc.nextInt();

        // Read swaps
        int[][] swaps = new int[m][2];
        for (int i = 0; i < m; i++) {
            swaps[i][0] = sc.nextInt();
            swaps[i][1] = sc.nextInt();
        }

        // Create instance and call the function
        MaximizeAlternatingSumUsingSwaps solution = new MaximizeAlternatingSumUsingSwaps();
        long result = solution.maxAlternatingSum(nums, swaps);

        System.out.println(result);

        sc.close();
    }
}
