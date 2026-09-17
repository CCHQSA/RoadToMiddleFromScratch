package Library_Management_System.service;

import Library_Management_System.exception.BookNotFoundException;
import Library_Management_System.model.Book;
import Library_Management_System.model.Library;

public class BookService {

    public void addBook(Library library, Book book) {
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

    public Book findBookById(Library library, Long id) {
        Book book = library.getBooks().get(id);

        if (book == null || !library.getBooks().containsKey(book.getId())) {
            throw new BookNotFoundException("Book not found: " + id);
        }


        return book;
    }

    public void removeBook(Library library, Long id) {
        Book book = findBookById(library, id);
        library.removeBook(book);
    }
}