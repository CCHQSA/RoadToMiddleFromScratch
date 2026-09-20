package library.management.system.ui;

import library.management.system.model.Loan;
import library.management.system.model.User;
import library.management.system.service.LoanService;
import library.management.system.service.UserService;

public class LoansMenu {

    private final ConsoleInput input;
    private final LoanService loanService;
    private final UserService userService;

    public LoansMenu(ConsoleInput input, LoanService loanService, UserService userService) {
        this.input = input;
        this.loanService = loanService;
        this.userService = userService;
    }

    public void run() {
        int choice;
        do {
            choice = showMenu();
            switch (choice) {
                case 1:
                    input.printResults(loanService.findAllLoans());
                    break;
                case 2:
                    input.printResults(loanService.findAllActive());
                    break;
                case 3:
                    findLoanById();
                    break;
                case 4:
                    showUserLoans(false);
                    break;
                case 5:
                    showUserLoans(true);
                    break;
                case 6:
                    loanService.findAllWithFines()
                        .forEach((loan, fine) ->
                                System.out.println(loan + " | Fine: " + fine));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private int showMenu() {
        System.out.println();
        System.out.println("===== LOANS =====");
        System.out.println("1. Show all loans");
        System.out.println("2. Show active loans");
        System.out.println("3. Find loan by ID");
        System.out.println("4. Show user's loan history");
        System.out.println("5. Show user's overdue loans");
        System.out.println("6. Show loans with fines");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");
        return input.nextInt();
    }

    private void findLoanById() {
        System.out.print("Enter Loan ID: ");
        System.out.println(loanService.findById(input.nextLong()));
    }

    private void showUserLoans(boolean overdueOnly) {
        System.out.print("Enter User ID: ");
        User user = userService.findUserById(input.nextLong());
        if (overdueOnly) {
            input.printResults(loanService.findOverdueLoansForUser(user));
        } else {
            input.printResults(loanService.historyLoansForUser(user));
        }
    }
}
