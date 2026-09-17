package Library_Management_System.service;

import Library_Management_System.exception.AlreadyHasActiveLoan;
import Library_Management_System.exception.BookAlreadyBorrowedException;
import Library_Management_System.model.*;

public class LoanService {

    private final Library library;
    private final UserService userService;
    private final BookService bookService;

    public LoanService(Library library, UserService userService, BookService bookService) {
        this.library = library;
        this.userService = userService;
        this.bookService = bookService;
    }

    public Loan createLoan(
            Long userId,
            Long bookId
            ){
        if (userId == null){
            throw new IllegalArgumentException("userId is null");
        }

        if (bookId == null){
            throw new IllegalArgumentException("bookId is null");
        }

        User user = userService.findUserById(library, userId);
        Book book = bookService.findBookById(library, bookId);

        if (!book.isAvailable()){
            throw new BookAlreadyBorrowedException("Book already borrowed: " + bookId);
        }


        boolean alreadyHasActiveLoan = library.getLoans().values()
                .stream()
                .anyMatch(loan ->
                        loan.getUser().getId().equals(userId)
                                && loan.getBook().getId().equals(bookId)
                                && loan.getStatus() == LoanStatus.ACTIVE
                );

        if(alreadyHasActiveLoan){
            throw new AlreadyHasActiveLoan("Already have an active loan");
        }

        Long loanId = library.getLoans().keySet().stream()
                .max(Long::compare)
                .orElse(null);

        Loan loan = new Loan(loanId != null ? loanId + 1: 1,book,user);

        book.borrow();
        library.addLoan(loan);

        return loan;
    }
}
