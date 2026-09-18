package Library_Management_System.service;

import Library_Management_System.exception.BookNotFoundException;
import Library_Management_System.model.Book;
import Library_Management_System.model.Genre;
import Library_Management_System.model.Library;

import java.util.List;

public class BookService {

    private final Library library;

    public BookService(Library library) {
        this.library = library;
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        if (library.getBooks().containsKey(book.getId())) {
            throw new IllegalArgumentException("Book ID already exists");
        }

        boolean isbnExists = library.getBooks()
                .values()
                .stream()
                .anyMatch(existingBook ->
                        existingBook.getIsbn().equals(book.getIsbn()));

        if (isbnExists) {
            throw new IllegalArgumentException("ISBN already exists");
        }

        library.addBook(book);
    }

    public Book findBookById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }

        Book book = library.getBooks().get(id);

        if (book == null) {
            throw new BookNotFoundException("Book not found: " + id);
        }

        return book;
    }

    public void removeBook(Long id) {
        Book book = findBookById(id);
        library.removeBook(book);
    }

    public Book findByISBN(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be null or blank");
        }

        return library.getBooks()
                .values()
                .stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() ->
                        new BookNotFoundException("ISBN not found: " + isbn));
    }

    public List<Book> findByTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Title cannot be null or blank"
            );
        }

        String searchTitle = title.trim().toLowerCase();

        return library.getBooks()
                .values()
                .stream()
                .filter(book ->
                        book.getTitle()
                                .toLowerCase()
                                .contains(searchTitle)
                )
                .toList();
    }

    public List<Book> findByAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException(
                    "Author cannot be null or blank"
            );
        }

        String searchAuthor = author.trim().toLowerCase();

        return library.getBooks()
                .values()
                .stream()
                .filter(book ->
                        book.getAuthors()
                                .stream()
                                .anyMatch(a ->
                                        a.getFullName()
                                                .toLowerCase()
                                                .contains(searchAuthor)
                                )
                )
                .toList();
    }

    public List<Book> findByGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException(
                    "Genre cannot be null or blank"
            );
        }

        Genre searchGenre;

        try {
            searchGenre = Genre.valueOf(
                    genre.trim().toUpperCase()
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Unknown genre: " + genre
            );
        }

        return library.getBooks()
                .values()
                .stream()
                .filter(book -> book.getGenre() == searchGenre)
                .toList();
    }

    public List<Book> findAvailableBooks() {
        return library.getBooks()
                .values()
                .stream()
                .filter(Book::isAvailable)
                .toList();
    }

    public List<Book> findBorrowedBooks() {
        return library.getBooks()
                .values()
                .stream()
                .filter(book -> !book.isAvailable())
                .toList();
    }

    public List<Book> findAllBooks() {
        return library.getBooks()
                .values()
                .stream()
                .toList();
    }
}