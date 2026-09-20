package library.management.system;

import library.management.system.model.Library;
import library.management.system.service.*;
import library.management.system.ui.ConsoleInput;
import library.management.system.ui.MainMenu;

public class Application {

    public static void main(String[] args) {
        Library library = new Library();
        BookService bookService = new BookService(library);
        UserService userService = new UserService(library);
        AuthorService authorService = new AuthorService(library);
        LoanService loanService = new LoanService(library, userService, bookService);
        LibraryStatisticsService statisticsService =
                new LibraryStatisticsService(bookService, userService, loanService);

        ConsoleInput input = new ConsoleInput();
        MainMenu mainMenu = new MainMenu(
                input,
                bookService,
                userService,
                authorService,
                loanService,
                statisticsService
        );

        mainMenu.run();
    }
}
