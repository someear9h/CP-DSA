public class LexicographicallySmallestNegatedPermutationthatSumstoTarget {
    public static int[] lexSmallestNegatedPerm(int n, long target) {
        long sum = (long) n * (n + 1) / 2;   
        long diff = sum - target;

        if(diff < 0 || diff % 2 != 0 || target < -sum) return new int[0];

        long negSum = diff /2;

        boolean[] isNeg = new boolean[n+1];
        for(int i = n; i >= 1; i--) {
            if(negSum >= i) {
                negSum = negSum - i;
                isNeg[i] = true;
            }
        }

        int[] res = new int[n];
        int idx = 0;

        for(int i = n; i >= 1; i--) {
            if(isNeg[i]) {
                res[idx++] = -i;
            }
        }

        for(int i = 1; i <= n; i++) {
            if(!isNeg[i]) {
                res[idx++] = i;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 3, target = 0;
        int[] res = lexSmallestNegatedPerm(n, target);
        for(int r : res) {
            System.out.print(r + " ");
        }

        System.out.println();
    }
}