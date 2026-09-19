package library.management.system.service;

import library.management.system.model.Author;
import library.management.system.model.Book;
import library.management.system.model.Genre;
import library.management.system.model.Library;
import library.management.system.model.Loan;
import library.management.system.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LibraryStatisticsServiceTest {

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

    @BeforeEach
    void setUp() {
        Library library = new Library();

        bookService = new BookService(library);
        userService = new UserService(library);
        loanService = new LoanService(library, userService, bookService);
        statisticsService = new LibraryStatisticsService(bookService, userService, loanService);

        Author author = new Author(1L, "Jonathan", "Joestar", LocalDate.of(1890, 3, 4));

        bookA = new Book(1L, "Book A", "ISBN-A", List.of(author), Genre.FICTION, LocalDate.of(2020, 1, 1));
        bookB = new Book(2L, "Book B", "ISBN-B", List.of(author), Genre.FICTION, LocalDate.of(2021, 1, 1));
        bookC = new Book(3L, "Book C", "ISBN-C", List.of(author), Genre.FANTASY, LocalDate.of(2022, 1, 1));

        bookService.addBook(bookA);
        bookService.addBook(bookB);
        bookService.addBook(bookC);

        userA = new User(1L, "John", "Doe", "john@example.com", LocalDate.now().minusDays(10));
        userB = new User(2L, "Jane", "Smith", "jane@example.com", LocalDate.now().minusDays(5));
        userC = new User(3L, "Jonathan", "Joestar", "jojo@example.com", LocalDate.now().minusDays(15));

        userService.addUser(userA);
        userService.addUser(userB);
        userService.addUser(userC);

        loanA = loanService.createLoan(userA.getId(), bookA.getId());
        loanB = loanService.createLoan(userB.getId(), bookB.getId());
    }

    @Test
    void getAllBooks_ShouldReturnEveryBook() {
        List<Book> books = statisticsService.getAllBooks();

        assertEquals(3, books.size());
        assertTrue(books.contains(bookA));
        assertTrue(books.contains(bookB));
        assertTrue(books.contains(bookC));
    }

    @Test
    void getAvailableBooks_ShouldReturnOnlyBooksWithoutActiveBorrowState() {
        assertEquals(List.of(bookC), statisticsService.getAvailableBooks());
    }

    @Test
    void getBorrowedBooks_ShouldReturnOnlyBorrowedBooks() {
        List<Book> books = statisticsService.getBorrowedBooks();

        assertEquals(2, books.size());
        assertTrue(books.contains(bookA));
        assertTrue(books.contains(bookB));
        assertFalse(books.contains(bookC));
    }

    @Test
    void getAllUsers_ShouldReturnEveryUser() {
        List<User> users = statisticsService.getAllUsers();

        assertEquals(3, users.size());
        assertTrue(users.contains(userA));
        assertTrue(users.contains(userB));
        assertTrue(users.contains(userC));
    }

    @Test
    void getAllLoans_ShouldReturnEveryLoan() {
        List<Loan> loans = statisticsService.getAllLoans();

        assertEquals(2, loans.size());
        assertTrue(loans.contains(loanA));
        assertTrue(loans.contains(loanB));
    }

    @Test
    void getTotalFine_ShouldReturnZero_WhenNoLoansHaveFines() {
        assertEquals(BigDecimal.ZERO, statisticsService.getTotalFine());
    }

    @Test
    void getMostBorrowedBooks_ShouldCountLoansByBook() {
        Map<Book, Long> mostBorrowed = statisticsService.getMostBorrowedBooks();

        assertEquals(2, mostBorrowed.size());
        assertEquals(1L, mostBorrowed.get(bookA));
        assertEquals(1L, mostBorrowed.get(bookB));
        assertFalse(mostBorrowed.containsKey(bookC));
    }

    @Test
    void getMostBorrowedBooks_ShouldReturnEmptyMap_WhenThereAreNoLoans() {
        LibraryStatisticsService emptyStatisticsService = createEmptyStatisticsService();

        assertTrue(emptyStatisticsService.getMostBorrowedBooks().isEmpty());
    }

    @Test
    void getMostActiveUsers_ShouldCountLoansByUser() {
        Map<User, Long> mostActiveUsers = statisticsService.getMostActiveUsers();

        assertEquals(2, mostActiveUsers.size());
        assertEquals(1L, mostActiveUsers.get(userA));
        assertEquals(1L, mostActiveUsers.get(userB));
        assertFalse(mostActiveUsers.containsKey(userC));
    }

    @Test
    void getMostActiveUsers_ShouldReturnEmptyMap_WhenThereAreNoLoans() {
        LibraryStatisticsService emptyStatisticsService = createEmptyStatisticsService();

        assertTrue(emptyStatisticsService.getMostActiveUsers().isEmpty());
    }

    @Test
    void getActiveLoans_ShouldReturnOnlyActiveLoans() {
        loanService.returnLoan(loanB.getId());

        assertEquals(List.of(loanA), statisticsService.getActiveLoans());
    }

    private LibraryStatisticsService createEmptyStatisticsService() {
        Library library = new Library();
        BookService bookService = new BookService(library);
        UserService userService = new UserService(library);
        LoanService loanService = new LoanService(library, userService, bookService);

        return new LibraryStatisticsService(bookService, userService, loanService);
    }
}
