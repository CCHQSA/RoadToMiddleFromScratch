package library.management.system.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Loan {
    private final Long id;
    private final Book book;
    private final User user;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;
    private LoanStatus status;

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

    public void returnLoan() {
        if (status == LoanStatus.RETURNED) {
            throw new IllegalStateException("Loan has already been returned");
        }

        this.returnDate = LocalDate.now();
        this.status = LoanStatus.RETURNED;
    }

    public boolean checkIfOverdue() {
        return (returnDate != null && returnDate.isAfter(dueDate))
                || (returnDate == null && LocalDate.now().isAfter(dueDate));
    }

    public int getDaysOverdue() {
        if (!checkIfOverdue()) {
            return 0;
        }

        LocalDate endDate;

        if (returnDate != null) {
            endDate = returnDate;
        } else {
            endDate = LocalDate.now();
        }

        return Math.toIntExact(
                ChronoUnit.DAYS.between(dueDate, endDate)
        );
    }
}
