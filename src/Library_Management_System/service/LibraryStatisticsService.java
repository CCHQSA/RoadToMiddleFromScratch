package Library_Management_System.service;

import Library_Management_System.model.Book;
import Library_Management_System.model.Loan;
import Library_Management_System.model.User;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryStatisticsService {

    private final BookService bookService;
    private final UserService userService;
    private final LoanService loanService;

    public LibraryStatisticsService(BookService bookService, UserService userService, LoanService loanService) {
        this.bookService = bookService;
        this.userService = userService;
        this.loanService = loanService;
    }

    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    public List<Book> getAvailableBooks() {
        return bookService.findAvailableBooks();
    }

    public List<Book> getBorrowedBooks(){
        return bookService.findBorrowedBooks();
    }

    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    public List<Loan> getAllLoans(){
        return loanService.findAllLoans();
    }

    public BigDecimal getTotalFine(){
        return loanService.findAllWithFines()
                .values()
                .stream()
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public Map<Book, Long> getMostBorrowedBooks() {
        Map<Book, Long> booksCount = loanService.findAllLoans()
                .stream()
                .collect(Collectors.groupingBy(Loan::getBook, Collectors.counting()));

        return booksCount.entrySet()
                .stream()
                .sorted(Map.Entry.<Book, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public Map<User, Long> getMostActiveUsers(){
        Map<User, Long> userCount = loanService.findAllLoans()
                .stream()
                .collect(Collectors.groupingBy(Loan::getUser, Collectors.counting()));

        return userCount.entrySet()
                .stream()
                .sorted(Map.Entry.<User, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public List<Loan> getActiveLoans(){
        return loanService.findAllActive();
    }


}
