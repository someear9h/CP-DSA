import java.util.TreeMap;

public class HandofStraights {
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if(n % groupSize != 0) return false;

        TreeMap<Integer, Integer> mp = new TreeMap<>();
        for(int h : hand) mp.put(h, mp.getOrDefault(h, 0) + 1);

        while(!mp.isEmpty()) {
            int i = mp.firstKey();

            for(int gz = 0; gz < groupSize; gz++) {
                int num = i + gz;

                if(!mp.containsKey(num)) return false;

                mp.put(num, mp.getOrDefault(num, 0) - 1);
                if(mp.get(num) == 0) mp.remove(num);
            }
        }

        return true;
    }    

    public static void main(String[] args) {
        int[] hand = {1,2,3,6,2,3,4,7,8};
        int gz = 3;

        System.out.println(isNStraightHand(hand, gz));
    }
}
