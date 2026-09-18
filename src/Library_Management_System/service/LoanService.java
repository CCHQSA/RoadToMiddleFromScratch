package Library_Management_System.service;

import Library_Management_System.exception.AlreadyHasActiveLoan;
import Library_Management_System.exception.BookAlreadyBorrowedException;
import Library_Management_System.exception.BookNotFoundException;
import Library_Management_System.exception.LoanNotFoundException;
import Library_Management_System.model.*;

import java.math.BigDecimal;
import java.util.List;

public class LoanService {

    private final Library library;
    private final UserService userService;
    private final BookService bookService;

    private static final BigDecimal FINE_PER_DAY = BigDecimal.valueOf(10);

    public LoanService(Library library, UserService userService, BookService bookService) {
        this.library = library;
        this.userService = userService;
        this.bookService = bookService;
    }

    public Loan createLoan(
            Long userId,
            Long bookId
    ) {
        if (userId == null) {
            throw new IllegalArgumentException("userId is null");
        }

        if (bookId == null) {
            throw new IllegalArgumentException("bookId is null");
        }

        User user = userService.findUserById(library, userId);
        Book book = bookService.findBookById(library, bookId);

        if (!book.isAvailable()) {
            throw new BookAlreadyBorrowedException("Book already borrowed: " + bookId);
        }


        if (hasAlreadyActiveLoan(userId, bookId)) {
            throw new AlreadyHasActiveLoan("Already have an active loan");
        }

        Long loanId = library.getLoans().keySet().stream()
                .max(Long::compare)
                .orElse(null);

        Loan loan = new Loan(loanId != null ? loanId + 1 : 1, book, user);

        book.borrow();
        library.addLoan(loan);

        return loan;
    }

    public void returnLoan(Long loanId) {
        if (loanId == null) {
            throw new IllegalArgumentException("loanId is null");
        }

        Loan loan = library.getLoans().get(loanId);

        if (loan == null) {
            throw new LoanNotFoundException("Loan not found: " + loanId);
        }

        if (loan.getStatus() != LoanStatus.ACTIVE) {
            throw new IllegalStateException("Loan is not active");
        }

        Book book = loan.getBook();

        loan.returnLoan();
        book.returnBook();
    }

    public Loan findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null");
        }

        Loan loan = library.getLoans().get(id);

        if (loan == null) {
            throw new LoanNotFoundException("Loan not found: " + id);
        }

        return loan;
    }

    public List<Loan> findActiveLoansForUser(User user){
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }

        return library.getLoans().values()
                .stream()
                .filter(loan ->
                        loan.getUser().getId().equals(user.getId()) &&
                        loan.getStatus() == LoanStatus.ACTIVE
                )
                .toList();
    }

    public List<Loan> historyLoansForUser(User user){
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }

        return library.getLoans().values()
                .stream()
                .filter(loan -> loan.getUser().getId().equals(user.getId()))
                .toList();
    }

    public List<Loan> findOverdueLoansForUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }

        return library.getLoans().values()
                .stream()
                .filter(loan ->
                        loan.getUser().getId().equals(user.getId()) &&
                                loan.checkIfOverdue()
                )
                .toList();
    }

    public Loan findLoanForBook(Long  bookId) {
        if (bookId == null) {
            throw new IllegalArgumentException("bookId is null");
        }

        if (!library.getBooks().containsKey(bookId)) {
            throw new BookNotFoundException("Book not found: " + bookId);
        }

        return library.getLoans().values()
                .stream()
                .filter(loan ->
                        loan.getBook().getId().equals(bookId) &&
                        loan.getStatus() ==  LoanStatus.ACTIVE
                )
                .findFirst()
                .orElseThrow(() -> new LoanNotFoundException("No current loan for book: " + bookId));
    }



    private boolean hasAlreadyActiveLoan(Long userId, Long bookId) {
        return library.getLoans().values()
                .stream()
                .anyMatch(loan ->
                        loan.getUser().getId().equals(userId)
                                && loan.getBook().getId().equals(bookId)
                                && loan.getStatus() == LoanStatus.ACTIVE
                );
    }

    public BigDecimal calculateFine(Loan loan) {
        if (loan == null) {
            throw new IllegalArgumentException("loan is null");
        }


        return FINE_PER_DAY.multiply(BigDecimal.valueOf(loan.getDaysOverdue()));
    }
}
