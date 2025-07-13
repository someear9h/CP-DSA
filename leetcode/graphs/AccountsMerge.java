import java.util.*;

class DisjointSet {
    int[] parent;
    int[] size;

    DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int findUPar(int node) {
        if(parent[node] == node) return node;
        return parent[node] = findUPar(parent[node]);
    }

    void unionBySize(int u, int v) {
        int up = findUPar(u), vp = findUPar(v);
        if(up == vp) return;

        if(size[up] < size[vp]) {
            parent[up] = vp;
            size[vp] += size[up];
        } else {
            parent[vp] = up;
            size[up] += size[vp];
        }
    }
}

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet dsu = new DisjointSet(n);
        Map<String, Integer> mp = new HashMap<>();

        // store the email and the index it was found
        for(int i = 0; i < n; i++) {
            for(int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if(mp.containsKey(mail) == false) {
                    mp.put(mail, i);
                } else {
                    dsu.unionBySize(i, mp.get(mail));
                }
            }
        }

        List<String>[] mergedMails = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            mergedMails[i] = new ArrayList<>();
        }

        for(Map.Entry<String, Integer> it : mp.entrySet()) {
            String mail = it.getKey();
            int user = dsu.findUPar(it.getValue());

            mergedMails[user].add(mail);
        }

        List<List<String>> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(mergedMails[i].isEmpty()) continue;

            List<String> temp = new ArrayList<>();
            Collections.sort(mergedMails[i]);
            temp.add(accounts.get(i).get(0)); // add the user
            for(String it : mergedMails[i]) {
                temp.add(it);
            }

            ans.add(temp);
        }

        return ans;
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(new ArrayList<>(Arrays.asList("John", "j1@com", "j2@com", "j3@com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "j4@com")));
        accounts.add(new ArrayList<>(Arrays.asList("John", "j3@com", "j4@com")));

        AccountsMerge sol = new AccountsMerge();
        List<List<String>> merged = sol.accountsMerge(accounts);

        for (List<String> account : merged) {
            System.out.println(account);
        }
    }
}