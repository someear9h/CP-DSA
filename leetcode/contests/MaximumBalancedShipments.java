public class MaximumBalancedShipments {
    public static int maxBalancedShipments(int[] weight) {
        int maxEle = (int)-1e9;
        int cnt = 0;

        for(int r = 0; r < weight.length; r++) {
            maxEle = Math.max(maxEle, weight[r]);

            if(weight[r] < maxEle) {
                cnt++;
                maxEle = 0;
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        int[] arr = {2,5,1,4,3};
        
        System.out.println(maxBalancedShipments(arr));
    }
}
