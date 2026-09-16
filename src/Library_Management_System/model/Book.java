package Library_Management_System.model;

import Library_Management_System.exception.BookAlreadyBorrowedException;
import Library_Management_System.exception.BookIsNotBorrowedException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Book {
    private final Long id;
    private final String title;
    private final String isbn;
    private final List<Author> authors;
    private final Genre genre;
    private final LocalDate publicationDate;
    private boolean available;

    public Book(
            Long id,
            String title,
            String isbn,
            List<Author> authors,
            Genre genre,
            LocalDate publicationDate
    ) {
        this.id = Objects.requireNonNull(id, "Book ID cannot be null");
        this.title = Objects.requireNonNull(title, "Book title cannot be null");
        this.isbn = Objects.requireNonNull(isbn, "ISBN cannot be null");
        this.authors = List.copyOf(Objects.requireNonNull(authors, "Authors cannot be null"));
        this.genre = Objects.requireNonNull(genre, "Genre cannot be null");
        this.publicationDate = Objects.requireNonNull(
                publicationDate,
                "Publication date cannot be null"
        );
        this.available = true;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<Author> getAuthors() {
        return List.copyOf(authors);
    }

    public Genre getGenre() {
        return genre;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public boolean isAvailable() {
        return available;
    }


    public void borrow() {
        if (!available) {
            throw new BookAlreadyBorrowedException
                    ("The book has already been borrowed.");
        }

        available = false;
    }

    public void returnBook() {
        if (available) {
            throw new BookIsNotBorrowedException
                    ("The book hasn't been borrowed.");
        }

        available = true;
    }


}
