package Library_Management_System.exception;

public class LoanAlreadyReturnedException extends RuntimeException {
    public LoanAlreadyReturnedException(String message) {
        super(message);
    }
}
