package library.management.system.service;

import Exceptions.UserRegistrationSystem.exceptions.InvalidEmailException;
import library.management.system.exception.EmailAlreadyExistsException;
import library.management.system.exception.UserNotFoundException;
import library.management.system.model.User;
import library.management.system.exception.UserAlreadyExistsException;
import library.management.system.model.Library;

import java.time.LocalDate;
import java.util.List;


public class UserService {

    private final Library library;

    public UserService(Library library) {
        this.library = library;
    }

    public void addUser(User user) {
        if(user == null){
            throw new UserNotFoundException("User not found");
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

        if (!user.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new InvalidEmailException("Invalid email address");
        }


        boolean emailExists = library.getUsers().values().stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()));

        if(emailExists){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        if (user.getRegistrationDate().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("User registration date cannot be in the future");
        }

        library.addUser(user);
    }

    public void removeUser(Long id) {

        if(!library.getUsers().containsKey(id)){
            throw new UserNotFoundException("User not found");
        }

        library.removeUser(id);
    }

    public User findUserById(Long id) {

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
