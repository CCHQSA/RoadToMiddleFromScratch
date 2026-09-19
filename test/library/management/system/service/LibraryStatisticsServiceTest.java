package library.management.system.service;

import library.management.system.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LibraryStatisticsServiceTest {

    private Library library;
    private BookService bookService;
    private UserService userService;
    private LoanService loanService;
    private LibraryStatisticsService statisticsService;

    private Book bookA;
    private Book bookB;
    private Book bookC;
    private User userA;
    private User userB;
    private User userC;
    private Loan loanA;
    private Loan loanB;
    private BigDecimal totalFine;

    @BeforeEach
    void setUp() {
        library = new Library();

        bookService = new BookService(library);
        userService = new UserService(library);
        loanService = new LoanService(library, userService, bookService);

        statisticsService = new LibraryStatisticsService(bookService, userService, loanService);

        Author author = new Author(1L, "FirstName", "LastName", LocalDate.of(2000, 1, 1));
        Genre genre1 = Genre.FICTION;
        Genre genre2 = Genre.FANTASY;

        bookA = new Book(1L, "Book A", "ISBN-A", List.of(author), genre1, LocalDate.now().minusMonths(1));
        bookB = new Book(2L, "Book B", "ISBN-B", List.of(author), genre1, LocalDate.now().minusMonths(2));
        bookC = new Book(3L, "Book C", "ISBN-C", List.of(author), genre2, LocalDate.now().minusMonths(3));

        library.addBook(bookA);
        library.addBook(bookB);
        library.addBook(bookC);

        userA = new User(1L, "John", "Doe", "john@example.com", LocalDate.now().minusDays(10));
        userB = new User(2L, "Jane", "Smith", "jane@example.com", LocalDate.now().minusDays(5));
        userC = new User(3L, "Jonathan", "Joestar", "jojo@example.com", LocalDate.now().minusDays(15));

        library.addUser(userA);
        library.addUser(userB);

        loanA = new Loan(1L, bookB, userA);
        bookA.borrow();

        loanB = new Loan(2L, bookC, userB);
        bookC.borrow();

        totalFine = statisticsService.getTotalFine();

        library.addLoan(loanA);
        library.addLoan(loanB);
    }


    @Test
    void testGetAvailableBooks() {
        List<Book> availableBooks = statisticsService.getAvailableBooks();

        assertNotNull(availableBooks, "Available books list should not be null");
        assertEquals(1, availableBooks.size(), "There should be exactly 1 available books");
        assertTrue(availableBooks.contains(bookB), "Should contain Book B");
        assertFalse(availableBooks.contains(bookA), "Should NOT contain Book A");
        assertFalse(availableBooks.contains(bookC), "Should NOT contain Book C");
    }

    @Test
    void testGetBorrowedBooks() {
        List<Book> borrowedBooks = statisticsService.getBorrowedBooks();

        assertNotNull(borrowedBooks, "Borrowed books list should not be null");
        assertTrue(borrowedBooks.contains(bookA), "Should contain Book A");
        assertTrue(borrowedBooks.contains(bookC), "Should contain Book C");
        assertFalse(borrowedBooks.contains(bookB), "Should NOT contain Book B");
    }

    @Test
    void testGetAllUsers() {
        List<User> allUsers = statisticsService.getAllUsers();

        assertNotNull(allUsers, "All users list should not be null");
        assertEquals(2, allUsers.size(), "There should be exactly 2 all users");
        assertTrue(allUsers.contains(userA), "Should contain User A");
        assertTrue(allUsers.contains(userB), "Should contain User B");
    }

    @Test
    void testGetAllLoans() {
        List<Loan> allLoans = statisticsService.getAllLoans();

        assertNotNull(allLoans, "All loans list should not be null");
        assertEquals(2, allLoans.size(), "There should be exactly 1 loans");
        assertTrue(allLoans.contains(loanA), "Should contain Loan A");
    }

    @Test
    void getTotalFine() {
        BigDecimal totalFine = statisticsService.getTotalFine();
        assertNotNull(totalFine, "Total fine should not be null");
    }

    @Test
    void getMostBorrowedBooks() {
        Map<Book, Long> mostBorrowed = statisticsService.getMostBorrowedBooks();

        assertNotNull(mostBorrowed, "Most borrowed Books list should not be null");
        assertEquals(2, mostBorrowed.size(), "There should be exactly 2 most borrowed books");
        assertTrue(mostBorrowed.containsKey(bookB), "Should contain Book B");
        assertTrue(mostBorrowed.containsKey(bookC), "Should contain Book C");
        assertFalse(mostBorrowed.containsKey(bookA), "Should NOT contain Book A");


    }

    @Test
    void getMostActiveUsers() {
        Map<User, Long> mostActiveUsers = statisticsService.getMostActiveUsers();

        assertNotNull(mostActiveUsers, "Most active users list should not be null");
        assertEquals(2, mostActiveUsers.size(), "There should be exactly 2 active users");
        assertTrue(mostActiveUsers.containsKey(userA), "Should contain User A");
        assertTrue(mostActiveUsers.containsKey(userB), "Should contain User B");
        assertFalse(mostActiveUsers.containsKey(userC), "Should NOT contain User C");
    }

    @Test
    void getActiveLoans() {
        List<Loan> activeLoans = statisticsService.getActiveLoans();

        assertNotNull(activeLoans, "Active loans list should not be null");
        assertEquals(2, activeLoans.size(), "There should be exactly 2 active loans");
        assertTrue(activeLoans.contains(loanA), "Should contain Loan A");
        assertTrue(activeLoans.contains(loanB), "Should contain Loan B");
    }
}
