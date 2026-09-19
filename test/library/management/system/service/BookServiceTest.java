package library.management.system.service;

import library.management.system.exception.BookNotFoundException;
import library.management.system.model.Author;
import library.management.system.model.Book;
import library.management.system.model.Genre;
import library.management.system.model.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private Library library;
    private BookService bookService;
    private Author authorA;
    private Author authorB;
    private Book bookA;
    private Book bookB;
    private Book borrowedBook;

    @BeforeEach
    void setUp() {
        library = new Library();
        bookService = new BookService(library);

        authorA = new Author(1L, "Jonathan", "Joestar", LocalDate.of(1890, 3, 4));
        authorB = new Author(2L, "Jotaro", "Kujo", LocalDate.of(1940, 5, 13));

        bookA = new Book(1L, "Java Basics", "ISBN-1", List.of(authorA), Genre.PROGRAMMING, LocalDate.of(2020, 1, 1));
        bookB = new Book(2L, "Fantasy Journey", "ISBN-2", List.of(authorB), Genre.FANTASY, LocalDate.of(2021, 2, 1));
        borrowedBook = new Book(3L, "Borrowed Book", "ISBN-3", List.of(authorA), Genre.FICTION, LocalDate.of(2022, 3, 1));
        borrowedBook.borrow();

        library.addBook(bookA);
        library.addBook(bookB);
        library.addBook(borrowedBook);
    }

    @Test
    void addBook_ShouldAddBook_WhenBookIsUnique() {
        Book book = new Book(4L, "Clean Code", "ISBN-4", List.of(authorA), Genre.PROGRAMMING, LocalDate.of(2008, 8, 1));

        bookService.addBook(book);

        assertEquals(book, bookService.findBookById(4L));
    }

    @Test
    void addBook_ShouldThrowException_WhenBookIsNull() {
        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(null));
    }

    @Test
    void addBook_ShouldThrowException_WhenIdAlreadyExists() {
        Book duplicateId = new Book(1L, "Another Title", "ISBN-4", List.of(authorB), Genre.OTHER, LocalDate.of(2023, 1, 1));

        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(duplicateId));
    }

    @Test
    void addBook_ShouldThrowException_WhenIsbnAlreadyExists() {
        Book duplicateIsbn = new Book(4L, "Another Title", "ISBN-1", List.of(authorB), Genre.OTHER, LocalDate.of(2023, 1, 1));

        assertThrows(IllegalArgumentException.class, () -> bookService.addBook(duplicateIsbn));
    }

    @Test
    void findBookById_ShouldReturnBook_WhenIdExists() {
        assertEquals(bookA, bookService.findBookById(1L));
    }

    @Test
    void findBookById_ShouldThrowException_WhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> bookService.findBookById(null));
    }

    @Test
    void findBookById_ShouldThrowException_WhenIdDoesNotExist() {
        assertThrows(BookNotFoundException.class, () -> bookService.findBookById(999L));
    }

    @Test
    void removeBook_ShouldRemoveBook_WhenIdExists() {
        bookService.removeBook(1L);

        assertThrows(BookNotFoundException.class, () -> bookService.findBookById(1L));
    }

    @Test
    void findByISBN_ShouldReturnBook_WhenIsbnExists() {
        assertEquals(bookB, bookService.findByISBN("ISBN-2"));
    }

    @Test
    void findByISBN_ShouldThrowException_WhenIsbnIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> bookService.findByISBN(" "));
    }

    @Test
    void findByISBN_ShouldThrowException_WhenIsbnIsNull() {
        assertThrows(IllegalArgumentException.class, () -> bookService.findByISBN(null));
    }

    @Test
    void findByISBN_ShouldThrowException_WhenIsbnDoesNotExist() {
        assertThrows(BookNotFoundException.class, () -> bookService.findByISBN("UNKNOWN"));
    }

    @Test
    void findByTitle_ShouldThrowException_WhenTitleIsNull() {
        assertThrows(IllegalArgumentException.class, () -> bookService.findByTitle(null));
    }

    @Test
    void findByTitle_ShouldThrowException_WhenTitleIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> bookService.findByTitle(" "));
    }

    @Test
    void findByTitle_ShouldReturnBooks_WhenTitlePartMatchesIgnoringCase() {
        List<Book> books = bookService.findByTitle("java");

        assertEquals(1, books.size());
        assertTrue(books.contains(bookA));
    }

    @Test
    void findByAuthor_ShouldThrowException_WhenAuthorIsNull() {
        assertThrows(IllegalArgumentException.class, () -> bookService.findByAuthor(null));
    }

    @Test
    void findByAuthor_ShouldThrowException_WhenAuthorIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> bookService.findByAuthor(" "));
    }

    @Test
    void findByAuthor_ShouldReturnBooks_WhenAuthorNameMatchesIgnoringCase() {
        List<Book> books = bookService.findByAuthor("joestar");

        assertEquals(2, books.size());
        assertTrue(books.contains(bookA));
        assertTrue(books.contains(borrowedBook));
    }

    @Test
    void findByGenre_ShouldThrowException_WhenGenreIsNull() {
        assertThrows(IllegalArgumentException.class, () -> bookService.findByGenre(null));
    }

    @Test
    void findByGenre_ShouldThrowException_WhenGenreIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> bookService.findByGenre(" "));
    }

    @Test
    void findByGenre_ShouldReturnBooks_WhenGenreMatchesIgnoringCase() {
        List<Book> books = bookService.findByGenre("fantasy");

        assertEquals(List.of(bookB), books);
    }

    @Test
    void findByGenre_ShouldThrowException_WhenGenreIsUnknown() {
        assertThrows(IllegalArgumentException.class, () -> bookService.findByGenre("detective"));
    }

    @Test
    void findAvailableBooks_ShouldReturnOnlyAvailableBooks() {
        List<Book> books = bookService.findAvailableBooks();

        assertEquals(2, books.size());
        assertTrue(books.contains(bookA));
        assertTrue(books.contains(bookB));
        assertFalse(books.contains(borrowedBook));
    }

    @Test
    void findBorrowedBooks_ShouldReturnOnlyBorrowedBooks() {
        assertEquals(List.of(borrowedBook), bookService.findBorrowedBooks());
    }

    @Test
    void findAllBooks_ShouldReturnEveryBook() {
        List<Book> books = bookService.findAllBooks();

        assertEquals(3, books.size());
        assertTrue(books.contains(bookA));
        assertTrue(books.contains(bookB));
        assertTrue(books.contains(borrowedBook));
    }
}
