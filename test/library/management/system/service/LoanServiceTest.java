package library.management.system.service;

import library.management.system.exception.BookAlreadyBorrowedException;
import library.management.system.exception.BookNotFoundException;
import library.management.system.exception.LoanNotFoundException;
import library.management.system.model.Author;
import library.management.system.model.Book;
import library.management.system.model.Genre;
import library.management.system.model.Library;
import library.management.system.model.Loan;
import library.management.system.model.LoanStatus;
import library.management.system.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceTest {

    private Library library;
    private BookService bookService;
    private UserService userService;
    private LoanService loanService;
    private User userA;
    private User userB;
    private Book bookA;
    private Book bookB;
    private Book bookC;

    @BeforeEach
    void setUp() {
        library = new Library();
        bookService = new BookService(library);
        userService = new UserService(library);
        loanService = new LoanService(library, userService, bookService);

        Author author = new Author(1L, "Jonathan", "Joestar", LocalDate.of(1890, 3, 4));
        userA = new User(1L, "John", "Doe", "john@example.com", LocalDate.now().minusDays(10));
        userB = new User(2L, "Jane", "Smith", "jane@example.com", LocalDate.now().minusDays(5));
        bookA = new Book(1L, "Java Basics", "ISBN-1", List.of(author), Genre.PROGRAMMING, LocalDate.of(2020, 1, 1));
        bookB = new Book(2L, "Fantasy Journey", "ISBN-2", List.of(author), Genre.FANTASY, LocalDate.of(2021, 2, 1));
        bookC = new Book(3L, "History Book", "ISBN-3", List.of(author), Genre.HISTORY, LocalDate.of(2022, 3, 1));

        library.addUser(userA);
        library.addUser(userB);
        library.addBook(bookA);
        library.addBook(bookB);
        library.addBook(bookC);
    }

    @Test
    void createLoan_ShouldCreateLoanAndBorrowBook_WhenUserAndBookExist() {
        Loan loan = loanService.createLoan(1L, 1L);

        assertEquals(1L, loan.getId());
        assertEquals(userA, loan.getUser());
        assertEquals(bookA, loan.getBook());
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
        assertFalse(bookA.isAvailable());
        assertEquals(loan, loanService.findById(1L));
    }

    @Test
    void createLoan_ShouldIncrementLoanIdFromExistingMaximum() {
        Loan firstLoan = new Loan(10L, bookB, userB);
        library.addLoan(firstLoan);

        Loan createdLoan = loanService.createLoan(1L, 1L);

        assertEquals(11L, createdLoan.getId());
    }

    @Test
    void createLoan_ShouldThrowException_WhenUserIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> loanService.createLoan(null, 1L));
    }

    @Test
    void createLoan_ShouldThrowException_WhenBookIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> loanService.createLoan(1L, null));
    }

    @Test
    void createLoan_ShouldThrowException_WhenBookAlreadyBorrowed() {
        loanService.createLoan(1L, 1L);

        assertThrows(BookAlreadyBorrowedException.class, () -> loanService.createLoan(2L, 1L));
    }

    @Test
    void returnLoan_ShouldReturnLoanAndMakeBookAvailable() {
        Loan loan = loanService.createLoan(1L, 1L);

        loanService.returnLoan(loan.getId());

        assertEquals(LoanStatus.RETURNED, loan.getStatus());
        assertTrue(bookA.isAvailable());
        assertNotNull(loan.getReturnDate());
    }

    @Test
    void returnLoan_ShouldThrowException_WhenLoanIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> loanService.returnLoan(null));
    }

    @Test
    void returnLoan_ShouldThrowException_WhenLoanDoesNotExist() {
        assertThrows(LoanNotFoundException.class, () -> loanService.returnLoan(999L));
    }

    @Test
    void returnLoan_ShouldThrowException_WhenLoanAlreadyReturned() {
        Loan loan = loanService.createLoan(1L, 1L);
        loanService.returnLoan(loan.getId());

        assertThrows(IllegalStateException.class, () -> loanService.returnLoan(loan.getId()));
    }

    @Test
    void findById_ShouldReturnLoan_WhenIdExists() {
        Loan loan = loanService.createLoan(1L, 1L);

        assertEquals(loan, loanService.findById(loan.getId()));
    }

    @Test
    void findById_ShouldThrowException_WhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> loanService.findById(null));
    }

    @Test
    void findById_ShouldThrowException_WhenLoanDoesNotExist() {
        assertThrows(LoanNotFoundException.class, () -> loanService.findById(999L));
    }

    @Test
    void findActiveLoansForUser_ShouldReturnOnlyActiveLoansForUser() {
        Loan activeLoan = loanService.createLoan(1L, 1L);
        Loan returnedLoan = loanService.createLoan(1L, 2L);
        loanService.createLoan(2L, 3L);
        loanService.returnLoan(returnedLoan.getId());

        List<Loan> loans = loanService.findActiveLoansForUser(userA);

        assertEquals(List.of(activeLoan), loans);
    }

    @Test
    void historyLoansForUser_ShouldReturnAllLoansForUser() {
        Loan loanA = loanService.createLoan(1L, 1L);
        Loan loanB = loanService.createLoan(1L, 2L);
        loanService.createLoan(2L, 3L);
        loanService.returnLoan(loanB.getId());

        List<Loan> history = loanService.historyLoansForUser(userA);

        assertEquals(2, history.size());
        assertTrue(history.contains(loanA));
        assertTrue(history.contains(loanB));
    }

    @Test
    void findOverdueLoansForUser_ShouldReturnEmptyList_WhenUserHasNoOverdueLoans() {
        loanService.createLoan(1L, 1L);

        assertTrue(loanService.findOverdueLoansForUser(userA).isEmpty());
    }

    @Test
    void findOverdueLoansForUser_ShouldReturnOverdueLoan() throws ReflectiveOperationException {
        Loan overdueLoan = loanService.createLoan(1L, 1L);
        makeOverdue(overdueLoan);

        assertEquals(List.of(overdueLoan), loanService.findOverdueLoansForUser(userA));
    }

    @Test
    void findLoanForBook_ShouldReturnActiveLoanForBook() {
        Loan loan = loanService.createLoan(1L, 1L);

        assertEquals(loan, loanService.findLoanForBook(1L));
    }

    @Test
    void findLoanForBook_ShouldThrowException_WhenBookDoesNotExist() {
        assertThrows(BookNotFoundException.class, () -> loanService.findLoanForBook(999L));
    }

    @Test
    void findLoanForBook_ShouldThrowException_WhenBookHasNoActiveLoan() {
        assertThrows(LoanNotFoundException.class, () -> loanService.findLoanForBook(1L));
    }

    @Test
    void calculateFine_ShouldReturnZero_WhenLoanIsNotOverdue() {
        Loan loan = loanService.createLoan(1L, 1L);

        assertEquals(BigDecimal.ZERO, loanService.calculateFine(loan));
    }

    @Test
    void calculateFine_ShouldReturnFineBasedOnDaysOverdue() throws ReflectiveOperationException {
        Loan loan = loanService.createLoan(1L, 1L);
        makeOverdue(loan);

        assertEquals(BigDecimal.valueOf(30), loanService.calculateFine(loan));
    }

    @Test
    void calculateFine_ShouldThrowException_WhenLoanIsNull() {
        assertThrows(IllegalArgumentException.class, () -> loanService.calculateFine(null));
    }

    @Test
    void findAllLoans_ShouldReturnEveryLoan() {
        Loan loanA = loanService.createLoan(1L, 1L);
        Loan loanB = loanService.createLoan(2L, 2L);

        List<Loan> loans = loanService.findAllLoans();

        assertEquals(2, loans.size());
        assertTrue(loans.contains(loanA));
        assertTrue(loans.contains(loanB));
    }

    @Test
    void findAllWithFines_ShouldReturnEmptyMap_WhenThereAreNoFines() {
        loanService.createLoan(1L, 1L);

        Map<Loan, BigDecimal> fines = loanService.findAllWithFines();

        assertTrue(fines.isEmpty());
    }

    @Test
    void findAllActive_ShouldReturnOnlyActiveLoans() {
        Loan activeLoan = loanService.createLoan(1L, 1L);
        Loan returnedLoan = loanService.createLoan(2L, 2L);
        loanService.returnLoan(returnedLoan.getId());

        assertEquals(List.of(activeLoan), loanService.findAllActive());
    }

    private void makeOverdue(Loan loan) throws ReflectiveOperationException {
        Field dueDate = Loan.class.getDeclaredField("dueDate");
        dueDate.setAccessible(true);
        dueDate.set(loan, LocalDate.now().minusDays(3));
    }
}
