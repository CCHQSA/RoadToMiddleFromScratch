package Library_Management_System.service;

import Library_Management_System.exception.AuthorNotFoundException;
import Library_Management_System.model.Author;
import Library_Management_System.model.Library;
import Library_Management_System.model.User;
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

        authorA = new Author(1L, "Jonathan", "Joestar", LocalDate.of(1890,  3, 4));
        authorB = new Author(2L, "Jotaro", "Joestar", LocalDate.of(1940,  5, 13));

        library.addAuthor(authorA);
        library.addAuthor(authorB);


        authorService = new AuthorService(library);
    }

    @Test
    void addAuthor() {
        Author authorC = new Author(3L, "Hisoka", "Morro", LocalDate.of(1940,  5, 13));
        Author authorD = new Author(4L, "Test", "NoAdd", LocalDate.of(1940,  5, 13));

        authorService.addAuthor(authorC);

        assertNotNull(library.getAuthors(), "Authors list should not be null");
        assertEquals(3, library.getAuthors().size(), "Authors list should contain 3");
        assertTrue(library.getAuthors().containsValue(authorC), "Authors list should contain authorC");
        assertTrue(library.getAuthors().containsValue(authorB), "Authors list should contain authorB");
        assertTrue(library.getAuthors().containsValue(authorA), "Authors list should contain authorA");
    }

    @Test
    void findAuthorById() {
        Author authorC = new Author(3L, "Test", "NoAdd", LocalDate.of(1940,  5, 13));

        assertEquals(authorA, authorService.findAuthorById(1L));
        assertEquals(authorB, authorService.findAuthorById(2L));
        assertThrows(AuthorNotFoundException.class, () -> authorService.findAuthorById(3L));

    }

    @Test
    void removeAuthor() {
        authorService.removeAuthor(1L);
        assertNotNull(library.getAuthors(), "Authors list should NOT be null");
        assertFalse(library.getAuthors().containsValue(authorA), "Authors list should contain authorA");
        assertTrue(library.getAuthors().containsValue(authorB), "Authors list should contain authorB");
    }

    @Test
    void findAuthorsByName() {
        List<Author> foundByFirstName = authorService.findAuthorsByName("Jonathan");
        List<Author> foundByLastName = authorService.findAuthorsByName("Joestar");
        List<Author> foundByFullName = authorService.findAuthorsByName("Jonathan Joestar");
        List<Author> notFound = authorService.findAuthorsByName("Name");

        assertNotNull(foundByFirstName, "foundByFirstName should NOT be null");
        assertNotNull(foundByLastName, "foundByLastName should NOT be null");
        assertNotNull(foundByFullName, "foundByFullName should NOT be null");
        assertTrue(notFound.isEmpty(), "notFound should be empty");
    }
}