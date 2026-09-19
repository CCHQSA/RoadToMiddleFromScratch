package library.management.system.model;

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

    public void addAuthor(Author author) {
        authors.put(author.getId(), author);
    }

    public Map<Long, Author> getAuthors(){
        return Collections.unmodifiableMap(authors);
    }

    public void removeAuthor(Long id) {
        authors.remove(id);
    }

    public Map<Long, User> getUsers(){
        return Collections.unmodifiableMap(users);
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void removeUser(Long id) {
        users.remove(id);
    }

    public void addLoan(Loan loan) {
        loans.put(loan.getId(), loan);
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan.getId());
    }

    public  Map<Long, Loan> getLoans() {
        return Collections.unmodifiableMap(loans);
    }


}