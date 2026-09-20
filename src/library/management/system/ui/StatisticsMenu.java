package library.management.system.ui;

import library.management.system.service.LibraryStatisticsService;

public class StatisticsMenu {

    private final ConsoleInput input;
    private final LibraryStatisticsService statisticsService;

    public StatisticsMenu(
            ConsoleInput input,
            LibraryStatisticsService statisticsService
    ) {
        this.input = input;
        this.statisticsService = statisticsService;
    }

    public void run() {
        int choice;
        do {
            choice = showMenu();
            switch (choice) {
                case 1:
                    System.out.println("Total books: " +
                            statisticsService.getAllBooks().size());
                    break;
                case 2:
                    System.out.println("Available books: " +
                            statisticsService.getAvailableBooks().size());
                    break;
                case 3:
                    System.out.println("Borrowed books: " +
                            statisticsService.getBorrowedBooks().size());
                    break;
                case 4:
                    System.out.println("Total users: " +
                            statisticsService.getAllUsers().size());
                    break;
                case 5:
                    System.out.println("Total loans: " +
                            statisticsService.getAllLoans().size());
                    break;
                case 6:
                    System.out.println("Total fines: " +
                            statisticsService.getTotalFine());
                    break;
                case 7:
                    statisticsService.getMostBorrowedBooks()
                        .forEach((book, count) ->
                                System.out.println(book + " | Borrows: " + count));
                    break;
                case 8:
                    statisticsService.getMostActiveUsers()
                        .forEach((user, count) ->
                                System.out.println(user + " | Loans: " + count));
                    break;
                case 9:
                    input.printResults(statisticsService.getActiveLoans());
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
        System.out.println("===== STATISTICS =====");
        System.out.println("1. Total books");
        System.out.println("2. Available books");
        System.out.println("3. Borrowed books");
        System.out.println("4. Total users");
        System.out.println("5. Total loans");
        System.out.println("6. Total fines");
        System.out.println("7. Most borrowed books");
        System.out.println("8. Most active users");
        System.out.println("9. Active loans");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");
        return input.nextInt();
    }
}
