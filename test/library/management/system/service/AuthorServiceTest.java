package library.management.system.service;

import library.management.system.exception.AuthorNotFoundException;
import library.management.system.model.Author;
import library.management.system.model.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceTest {

    private AuthorService authorService;
    private Library library;
    private Author authorA;
    private Author authorB;

    @BeforeEach
    void setUp() {
        library = new Library();

        authorA = new Author(1L, "Jonathan", "Joestar", LocalDate.of(1890, 3, 4));
        authorB = new Author(2L, "Jotaro", "Joestar", LocalDate.of(1940, 5, 13));

        library.addAuthor(authorA);
        library.addAuthor(authorB);

        authorService = new AuthorService(library);
    }

    @Test
    void addAuthor_ShouldSuccessfullyAddAuthor() {
        Author authorC = new Author(3L, "Hisoka", "Morro", LocalDate.of(1940, 5, 13));

        authorService.addAuthor(authorC);

        assertEquals(authorC, authorService.findAuthorById(3L), "Should find the newly added author");
    }

    @Test
    void findAuthorById_ShouldReturnAuthor_WhenIdExists() {
        assertEquals(authorA, authorService.findAuthorById(1L));
        assertEquals(authorB, authorService.findAuthorById(2L));
    }

    @Test
    void findAuthorById_ShouldThrowException_WhenIdDoesNotExist() {
        assertThrows(AuthorNotFoundException.class, () -> authorService.findAuthorById(999L));
    }

    @Test
    void removeAuthor_ShouldDeleteAuthorFromLibrary() {
        authorService.removeAuthor(1L);

        assertThrows(AuthorNotFoundException.class, () -> authorService.findAuthorById(1L),
                "Should throw exception because author was removed");
    }

    @Test
    void findAuthorsByName_ShouldReturnCorrectAuthors() {
        List<Author> foundByFirstName = authorService.findAuthorsByName("Jonathan");
        List<Author> foundByLastName = authorService.findAuthorsByName("Joestar");
        List<Author> notFound = authorService.findAuthorsByName("NonExistingName");

        assertEquals(1, foundByFirstName.size());
        assertTrue(foundByFirstName.contains(authorA));

        assertEquals(2, foundByLastName.size());
        assertTrue(foundByLastName.contains(authorA));
        assertTrue(foundByLastName.contains(authorB));

        assertTrue(notFound.isEmpty(), "Should return an empty list if no match found");
    }
}
