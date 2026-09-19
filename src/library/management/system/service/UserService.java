package library.management.system.service;

import library.management.system.exception.UserNotFoundException;
import library.management.system.model.User;
import library.management.system.exception.UserAlreadyExistsException;
import library.management.system.model.Library;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserService {

    private final Library library;

    public UserService(Library library) {
        this.library = library;
    }

    public void addUser(User user) {
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

    public void removeUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        if(!library.getUsers().containsKey(id)){
            throw new UserNotFoundException("User not found");
        }

        library.removeUser(id);
    }

    public User findUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot null");
        }

        if (!library.getUsers().containsKey(id)) {
            throw new UserNotFoundException("User not found");
        }

        return library.getUsers().get(id);
    }

    public List<User> findUserByName(String name) {
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

    public List<User> findAllUsers() {
        return library.getUsers().values()
                .stream().toList();
    }
}
