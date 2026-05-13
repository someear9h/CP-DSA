package aggregation_and_composition.aggregation;

public class Book {
    String title;
    int pages;

    Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }

    void showBookInfo() {
        System.out.printf("%s (%d pages) \n", this.title, this.pages);
    }
}
