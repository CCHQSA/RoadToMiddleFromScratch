package Library_Management_System.exception;

public class AlreadyHasActiveLoan extends RuntimeException {
    public AlreadyHasActiveLoan(String message) {
        super(message);
    }
}
