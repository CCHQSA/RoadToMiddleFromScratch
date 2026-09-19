package library.management.system.exception;

public class AlreadyHasActiveLoan extends RuntimeException {
    public AlreadyHasActiveLoan(String message) {
        super(message);
    }
}
