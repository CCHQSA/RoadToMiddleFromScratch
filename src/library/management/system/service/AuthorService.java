package library.management.system.service;

import library.management.system.exception.AuthorAlreadyExistsException;
import library.management.system.exception.AuthorNotFoundException;
import library.management.system.model.Author;
import library.management.system.model.Library;
import java.util.List;

public class AuthorService {

    private final Library library;

    public AuthorService(Library library) {
        this.library = library;
    }

    public void addAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }

        if (library.getAuthors().containsKey(author.getId())) {
            throw new AuthorAlreadyExistsException("Author already exists");
        }

        library.addAuthor(author);
    }

    public Author findAuthorById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Author ID cannot be null");
        }

        if (!library.getAuthors().containsKey(id)) {
            throw new AuthorNotFoundException("Author not found");
        }

        return library.getAuthors().get(id);
    }

    public void removeAuthor(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Author ID cannot be null");
        }

        if (!library.getAuthors().containsKey(id)) {
            throw new AuthorNotFoundException("Author not found");
        }

        library.removeAuthor(id);
    }

    public List<Author> findAuthorsByName(String name) {
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