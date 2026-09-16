package Library_Management_System.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Library {

    private final Map<Long, Book> books;
    private final Map<Long, User> users;
    private final Map<Long, Author> authors;
    private final Map<Long, Loan> loans;

    public Library() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.authors = new HashMap<>();
        this.loans = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void removeBook(Book book) {
        books.remove(book.getId());
    }

    public Map<Long, Book> getBooks() {
        return Collections.unmodifiableMap(books);
    }
}