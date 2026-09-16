package Library_Management_System.exception;

public class BookIsNotBorrowedException extends RuntimeException {
    public BookIsNotBorrowedException(String message) {
        super(message);
    }
}
