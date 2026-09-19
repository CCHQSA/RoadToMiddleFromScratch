package library.management.system.service;

import library.management.system.exception.UserAlreadyExistsException;
import library.management.system.exception.UserNotFoundException;
import library.management.system.model.Library;
import library.management.system.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private Library library;
    private UserService userService;
    private User userA;
    private User userB;

    @BeforeEach
    void setUp() {
        library = new Library();
        userService = new UserService(library);

        userA = new User(1L, "John", "Doe", "john@example.com", LocalDate.now().minusDays(10));
        userB = new User(2L, "Jane", "Smith", "jane@example.com", LocalDate.now().minusDays(5));

        library.addUser(userA);
        library.addUser(userB);
    }

    @Test
    void addUser_ShouldAddUser_WhenUserIsUnique() {
        User user = new User(3L, "Jonathan", "Joestar", "jojo@example.com", LocalDate.now());

        userService.addUser(user);

        assertEquals(user, userService.findUserById(3L));
    }

    @Test
    void addUser_ShouldThrowException_WhenUserIsNull() {
        assertThrows(IllegalArgumentException.class, () -> userService.addUser(null));
    }

    @Test
    void addUser_ShouldThrowException_WhenIdAlreadyExists() {
        User duplicateId = new User(1L, "Other", "Person", "other@example.com", LocalDate.now());

        assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(duplicateId));
    }

    @Test
    void addUser_ShouldThrowException_WhenEmailAlreadyExists() {
        User duplicateEmail = new User(3L, "Other", "Person", "john@example.com", LocalDate.now());

        assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(duplicateEmail));
    }

    @Test
    void findUserById_ShouldReturnUser_WhenIdExists() {
        assertEquals(userA, userService.findUserById(1L));
    }

    @Test
    void findUserById_ShouldThrowException_WhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> userService.findUserById(null));
    }

    @Test
    void findUserById_ShouldThrowException_WhenIdDoesNotExist() {
        assertThrows(UserNotFoundException.class, () -> userService.findUserById(999L));
    }

    @Test
    void removeUser_ShouldRemoveUser_WhenIdExists() {
        userService.removeUser(1L);

        assertThrows(UserNotFoundException.class, () -> userService.findUserById(1L));
    }

    @Test
    void removeUser_ShouldThrowException_WhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> userService.removeUser(null));
    }

    @Test
    void removeUser_ShouldThrowException_WhenUserDoesNotExist() {
        assertThrows(UserNotFoundException.class, () -> userService.removeUser(999L));
    }

    @Test
    void findUserByName_ShouldReturnUsers_WhenFirstLastOrFullNameMatchesIgnoringCase() {
        assertEquals(List.of(userA), userService.findUserByName("john"));
        assertEquals(List.of(userB), userService.findUserByName("smith"));
        assertEquals(List.of(userA), userService.findUserByName("John Doe"));
    }

    @Test
    void findUserByName_ShouldThrowException_WhenNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> userService.findUserByName(" "));
    }

    @Test
    void findUserByName_ShouldThrowException_WhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> userService.findUserByName(null));
    }

    @Test
    void findAllUsers_ShouldReturnEveryUser() {
        List<User> users = userService.findAllUsers();

        assertEquals(2, users.size());
        assertTrue(users.contains(userA));
        assertTrue(users.contains(userB));
    }
}
