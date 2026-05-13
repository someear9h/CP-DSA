package static_explained;

public class Main {
    public static void main(String[] args) {

        // static -> makes a variable or method belong to a class rather than an object
        // commonly used for utility methods or shared objects

        Friend f = new Friend("Samarth");
        Friend f1 = new Friend("Sama");
        Friend f2 = new Friend("Samo");

        System.out.println(Friend.numOfFriends);
        System.out.println(Friend.numOfFriends);
        System.out.println(Friend.numOfFriends);

        Friend.showFriends();
    }
}
