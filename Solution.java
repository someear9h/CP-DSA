public class Solution {
    public static void main(String[] args) {
        int myScore = 10;

        changeScore(myScore);

        System.out.println(myScore);
    }

    private static void changeScore(int myScore) {
        myScore = 100;
        System.out.println(myScore + "-> in function");
    }
}