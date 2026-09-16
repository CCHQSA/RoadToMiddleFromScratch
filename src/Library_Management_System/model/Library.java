package Library_Management_System.model;

import java.util.List;
import java.util.Map;

public class Library {
    private Map<Long, Book> books;
    private Map<Long, User> users;
    private Map<Long, Author>  authors;
    private Map<Long, Loan> loans;
}
