package static_explained;

public class Friend {
    String name;
    static int numOfFriends;

    Friend(String name) {
        this.name = name;
        numOfFriends++;
    }

    public static void showFriends() {
        System.out.println("You have " + numOfFriends + " friends");
    }
}
