package Exceptions.UserRegistrationSystem;

import Exceptions.UserRegistrationSystem.exceptions.InvalidEmailException;
import Exceptions.UserRegistrationSystem.exceptions.UsernameAlreadyExistsException;
import Exceptions.UserRegistrationSystem.exceptions.WeakPasswordException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private final UserRepository userRepository;
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private final static Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private static final String PASSWORD_PATTERN =
            "^(?=.*[A-Z])(?=.*\\d)(?=.*[+_.-])[A-Za-z\\d+_.-]{8,32}$";
    private final static Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (userRepository.checkUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException("Username '" + user.getUsername() + "' already exists");
        }
        isValidEmail(user.getEmail());
        isValidPassword(user.getPassword());
        userRepository.addUser(user);
    }

    public static void isValidEmail(String email) {
        if (email == null || !emailPattern.matcher(email).matches()) {
            throw new InvalidEmailException("Invalid Email: cannot be null or malformed");
        }
    }

    public static void isValidPassword(String password) {
        if (password == null || !passwordPattern.matcher(password).matches()) {
            throw new WeakPasswordException("Password too weak or empty");
        }
    }
}

