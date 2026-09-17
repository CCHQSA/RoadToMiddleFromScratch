package Library_Management_System.exception;

public class AuthorAlreadyExistsException extends RuntimeException {
    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
