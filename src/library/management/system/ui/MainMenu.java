package library.management.system.ui;

import library.management.system.exception.InvalidChoiceException;
import library.management.system.model.Loan;
import library.management.system.service.*;

public class MainMenu {

    private final ConsoleInput input;
    private final BooksMenu booksMenu;
    private final UsersMenu usersMenu;
    private final LoansMenu loansMenu;
    private final StatisticsMenu statisticsMenu;
    private final LoanService loanService;

    public MainMenu(
            ConsoleInput input,
            BookService bookService,
            UserService userService,
            AuthorService authorService,
            LoanService loanService,
            LibraryStatisticsService statisticsService
    ) {
        this.input = input;
        this.loanService = loanService;
        this.booksMenu = new BooksMenu(input, bookService, authorService, statisticsService);
        this.usersMenu = new UsersMenu(input, userService);
        this.loansMenu = new LoansMenu(input, loanService, userService);
        this.statisticsMenu = new StatisticsMenu(input, statisticsService);
    }

    public void run() {
        while (true) {
            switch (showMenu()) {
                case 1:
                    booksMenu.run();
                    break;
                case 2:
                    usersMenu.run();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    loansMenu.run();
                    break;
                case 6:
                    statisticsMenu.run();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    throw new InvalidChoiceException("Invalid choice");
            }
        }
    }

    private int showMenu() {
        System.out.println();
        System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");
        System.out.println("1. Books");
        System.out.println("2. Users");
        System.out.println("3. Borrow book");
        System.out.println("4. Return book");
        System.out.println("5. Loans");
        System.out.println("6. Statistics");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
        return input.nextInt();
    }

    private void borrowBook() {
        System.out.print("Enter User ID: ");
        Long userId = input.nextLong();
        System.out.print("Enter Book ID: ");
        Long bookId = input.nextLong();

        Loan loan = loanService.createLoan(userId, bookId);
        System.out.println("Book borrowed successfully. Loan ID: " + loan.getId());
    }

    private void returnBook() {
        System.out.print("Enter Loan ID: ");
        Long loanId = input.nextLong();
        loanService.returnLoan(loanId);
        System.out.println("Book returned successfully.");
    }
}
