#include <iostream>
using namespace std;

struct Book {
    string title;
    string author;
    string publication;

    Book() {}

    Book(string t, string a, string pub) {
        title = t;
        author = a;
        publication = pub;
    }
};

// linear search
void linearSearch(Book books[], int n, string targetAuthor) {
    bool found = false;

    for(int i = 0; i < n; i++) {
        if(books[i].author == targetAuthor) {
            cout << "Book found! " << endl;
            cout << "Title: " << books[i].title << endl;
            cout << "Author name: " << books[i].author << endl;
            cout << "Publication: " << books[i].publication << endl;
            found = true;
        }
    }

    if(!found) {
        cout << "No Books found" << endl;
    }
}

int main() {
    int n;
    cout << "Enter number of books: ";
    cin >> n;

    Book books[n];

    for(int i = 0; i < n; i++) {
        string title, author, publication;
        cout << "Enter the title of book: ";
        cin >> title;

        cout << "Enter the author name: ";
        cin >> author;

        cout << "Enter publication: ";
        cin >> publication;
        books[i] = Book(title, author, publication);
    }

    string targetAuthor;
    cout << "Enter author of the book u want to search: ";
    cin >> targetAuthor;

    linearSearch(books, n, targetAuthor);

    return 0;
}