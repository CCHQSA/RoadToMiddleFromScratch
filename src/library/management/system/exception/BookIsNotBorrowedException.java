package library.management.system.exception;

public class BookIsNotBorrowedException extends RuntimeException {
    public BookIsNotBorrowedException(String message) {
        super(message);
    }
}
