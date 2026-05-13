package aggregation_and_composition.aggregation;

public class Library {
    String name;
    String owner;
    Book[] books;

    Library(String name, String owner, Book[] books) {
        this.name = name;
        this.owner = owner;
        this.books = books;
    }

    void showLibraryInfo() {
        System.out.printf("%s owns the %s \n", this.owner, this.name);
        System.out.printf("%s has following books available: \n", this.name);

        for(Book book : books) {
            book.showBookInfo();
        }
    }
}
