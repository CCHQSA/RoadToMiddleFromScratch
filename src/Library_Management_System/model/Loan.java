package Library_Management_System.model;

import java.time.LocalDate;

public class Loan {
    private final long id;
    private Book book;
    private User user;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private LoanStatus status;

    public Loan(User user,Book book, long id){
        this.user = user;
        this.id = id;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.dueDate = this.borrowDate.plusDays(14);
        this.status = LoanStatus.ACTIVE;
    }

}
