package Library_Management_System.exception;

public class InvalidLoanException extends RuntimeException {
    public InvalidLoanException(String message) {
        super(message);
    }
}
