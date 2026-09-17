package Library_Management_System.service;

import Library_Management_System.exception.UserNotFoundException;
import Library_Management_System.model.User;
import Library_Management_System.exception.UserAlreadyExistsException;
import Library_Management_System.model.Library;

import java.util.List;
import java.util.Objects;

public class UserService {

    public void addUser(Library library, User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (library.getUsers().containsKey(user.getId())) {
            throw new UserAlreadyExistsException("User already exists");
        }

        if (
                library.getUsers().values()
                        .stream()
                        .anyMatch(u -> u.getEmail().equals(user.getEmail()))
        ){
            throw new UserAlreadyExistsException("User already exists");
        }

        library.addUser(user);
    }

    public void removeUser(Library library, Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        if(!library.getUsers().containsKey(id)){
            throw new UserNotFoundException("User not found");
        }

        library.removeUser(id);
    }

    public User findUserById(Library library, Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot null");
        }

        if (!library.getUsers().containsKey(id)) {
            throw new UserNotFoundException("User not found");
        }

        return library.getUsers().get(id);
    }

    public List<User> findUserByName(Library library, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name cannot be null");
        }
        String trimmedName = name.trim();

        return library.getUsers().values()
                .stream()
                .filter(user ->
                        user.getFirstName().equalsIgnoreCase(trimmedName) ||
                        user.getFullName().equalsIgnoreCase(trimmedName) ||
                        user.getLastName().equalsIgnoreCase(trimmedName)
                        )
                .toList();
    }
}
