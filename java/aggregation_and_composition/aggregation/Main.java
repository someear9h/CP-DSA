package aggregation_and_composition.aggregation;

public class Main {
    public static void main(String[] args) {
        /*
        * Aggregation: represents a 'has-a' relationship between objects
        * One object contains another object as part of its structure
        * both objects can exist independently
        * */

        Book book1 = new Book("My Book", 20);
        Book book2 = new Book("Your Book", 30);
        Book book3 = new Book("What Book", 40);

        Book[] books = {book1, book2, book3};

        Library library = new Library("City Library", "Samarth", books);
        library.showLibraryInfo();
    }
}
