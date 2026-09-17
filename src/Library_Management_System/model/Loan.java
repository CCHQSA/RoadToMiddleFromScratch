package Library_Management_System.model;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {
    private final Long id;
    private final Book book;
    private final User user;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private final LocalDate returnDate;
    private final LoanStatus status;

    public Loan(Long id, Book book, User user) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.book = Objects.requireNonNull(book, "Book cannot be null");
        this.user = Objects.requireNonNull(user, "User cannot be null");
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(14);
        this.returnDate = null;
        this.status = LoanStatus.ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LoanStatus getStatus() {
        return status;
    }
}
