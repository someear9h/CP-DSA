package OOP.class_and_objects;

class User {
    String username;
    String email;
    int age;

    User() {
        this.username = "username";
        this.email = "email@mail.com";
        this.age = 19;
    }

    User(String username) {
        this.username = username;
        this.email = "Not provided";
        this.age = 0;
    }

    User(String username, String email) {
        this.username = username;
        this.email = email;
        this.age = 0;
    }

    User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
}

public class OverloadedConstructor {
    public static void main(String[] args) {
        User user = new User();
        System.out.println(user.username);
        System.out.println(user.email);
        System.out.println(user.age);


        User user1 = new User("someear1h");

        System.out.println(user1.username);
        System.out.println(user1.email);
        System.out.println(user1.age);

        User user2 = new User("Cool", "cool@mail.com");
        System.out.println(user2.username);
        System.out.println(user2.email);
        System.out.println(user2.age);

        User user3 =  new User("has_everything", "every@mail.com", 21);
        System.out.println(user3.username);
        System.out.println(user3.email);
        System.out.println(user3.age);

        // an array of objects
        User[] users = {user1, user2, user3};
        for(User u : users) {
            System.out.printf("%s's age is %d\n", u.username, u.age);
        }
    }
}
