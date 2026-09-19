package library.management.system.service;

import library.management.system.exception.AuthorAlreadyExistsException;
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
    private Author authorA;
    private Author authorB;

    @BeforeEach
    void setUp() {
        Library library = new Library();
        authorService = new AuthorService(library);

        authorA = new Author(1L, "Jonathan", "Joestar", LocalDate.of(1890, 3, 4));
        authorB = new Author(2L, "Jotaro", "Joestar", LocalDate.of(1940, 5, 13));

        authorService.addAuthor(authorA);
        authorService.addAuthor(authorB);
    }

    @Test
    void addAuthor_ShouldAddAuthor_WhenAuthorIsNew() {
        Author author = new Author(3L, "Hisoka", "Morro", LocalDate.of(1973, 6, 6));

        authorService.addAuthor(author);

        assertEquals(author, authorService.findAuthorById(3L));
    }

    @Test
    void addAuthor_ShouldThrowException_WhenAuthorIsNull() {
        assertThrows(IllegalArgumentException.class, () -> authorService.addAuthor(null));
    }

    @Test
    void addAuthor_ShouldThrowException_WhenIdAlreadyExists() {
        Author duplicateId = new Author(1L, "Joseph", "Joestar", LocalDate.of(1920, 9, 27));

        assertThrows(AuthorAlreadyExistsException.class, () -> authorService.addAuthor(duplicateId));
    }

    @Test
    void findAuthorById_ShouldReturnAuthor_WhenIdExists() {
        assertEquals(authorA, authorService.findAuthorById(1L));
        assertEquals(authorB, authorService.findAuthorById(2L));
    }

    @Test
    void findAuthorById_ShouldThrowException_WhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> authorService.findAuthorById(null));
    }

    @Test
    void findAuthorById_ShouldThrowException_WhenIdDoesNotExist() {
        assertThrows(AuthorNotFoundException.class, () -> authorService.findAuthorById(999L));
    }

    @Test
    void removeAuthor_ShouldRemoveAuthor_WhenIdExists() {
        authorService.removeAuthor(1L);

        assertThrows(AuthorNotFoundException.class, () -> authorService.findAuthorById(1L));
        assertEquals(authorB, authorService.findAuthorById(2L));
    }

    @Test
    void removeAuthor_ShouldThrowException_WhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> authorService.removeAuthor(null));
    }

    @Test
    void removeAuthor_ShouldThrowException_WhenIdDoesNotExist() {
        assertThrows(AuthorNotFoundException.class, () -> authorService.removeAuthor(999L));
    }

    @Test
    void findAuthorsByName_ShouldReturnAuthor_WhenFirstNameMatchesIgnoringCase() {
        assertEquals(List.of(authorA), authorService.findAuthorsByName("jonathan"));
    }

    @Test
    void findAuthorsByName_ShouldReturnAuthors_WhenLastNameMatchesIgnoringCase() {
        List<Author> authors = authorService.findAuthorsByName("JOESTAR");

        assertEquals(2, authors.size());
        assertTrue(authors.contains(authorA));
        assertTrue(authors.contains(authorB));
    }

    @Test
    void findAuthorsByName_ShouldReturnAuthor_WhenFullNameMatchesIgnoringCase() {
        assertEquals(List.of(authorB), authorService.findAuthorsByName("jotaro joestar"));
    }

    @Test
    void findAuthorsByName_ShouldTrimSearchTerm() {
        assertEquals(List.of(authorA), authorService.findAuthorsByName("  Jonathan  "));
    }

    @Test
    void findAuthorsByName_ShouldReturnEmptyList_WhenNoAuthorMatches() {
        assertTrue(authorService.findAuthorsByName("Dio Brando").isEmpty());
    }

    @Test
    void findAuthorsByName_ShouldThrowException_WhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> authorService.findAuthorsByName(null));
    }

    @Test
    void findAuthorsByName_ShouldThrowException_WhenNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> authorService.findAuthorsByName(" "));
    }
}
