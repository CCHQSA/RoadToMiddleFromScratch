package Library_Management_System.service;

import Library_Management_System.exception.AuthorAlreadyExistsException;
import Library_Management_System.exception.AuthorNotFoundException;
import Library_Management_System.model.Author;
import Library_Management_System.model.Library;
import java.util.List;

public class AuthorService {

    public void addAuthor(Library library, Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }

        if (library.getAuthors().containsKey(author.getId())) {
            throw new AuthorAlreadyExistsException("Author already exists");
        }

        library.addAuthor(author);
    }

    public Author findAuthorById(Library library, Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Author ID cannot be null");
        }

        if (!library.getAuthors().containsKey(id)) {
            throw new AuthorNotFoundException("Author not found");
        }

        return library.getAuthors().get(id);
    }

    public void removeAuthor(Library library, Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Author ID cannot be null");
        }

        if (!library.getAuthors().containsKey(id)) {
            throw new AuthorNotFoundException("Author not found");
        }

        library.removeAuthor(id);
    }

    public List<Author> findAuthorsByName(Library library, String name) {
        if (name == null ||  name.isBlank()) {
            throw new IllegalArgumentException("Author name cannot be null");
        }
        String trimmedName = name.trim();
        return library.getAuthors().values()
                .stream()
                .filter(author ->
                        author.getFullName().equalsIgnoreCase(trimmedName) ||
                        author.getFirstName().equalsIgnoreCase(trimmedName) ||
                        author.getLastName().equalsIgnoreCase(trimmedName)
                ).toList();
    }
}