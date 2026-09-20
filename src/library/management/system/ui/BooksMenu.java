package library.management.system.ui;

import library.management.system.model.*;
import library.management.system.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BooksMenu {

    private final ConsoleInput input;
    private final BookService bookService;
    private final AuthorService authorService;
    private final LibraryStatisticsService statisticsService;

    public BooksMenu(
            ConsoleInput input,
            BookService bookService,
            AuthorService authorService,
            LibraryStatisticsService statisticsService
    ) {
        this.input = input;
        this.bookService = bookService;
        this.authorService = authorService;
        this.statisticsService = statisticsService;
    }

    public void run() {
        int choice;
        do {
            choice = showMenu();
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    findBookById();
                    break;
                case 3:
                    findBookByIsbn();
                    break;
                case 4:
                    searchByTitle();
                    break;
                case 5:
                    searchByAuthor();
                    break;
                case 6:
                    searchByGenre();
                    break;
                case 7:
                    showAvailable();
                    break;
                case 8:
                    showBorrowed();
                    break;
                case 0:
                    System.out.println("Returning to main menu");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private int showMenu() {
        System.out.println();
        System.out.println("===== BOOKS =====");
        System.out.println("1. Add book");
        System.out.println("2. Find book by ID");
        System.out.println("3. Find by ISBN");
        System.out.println("4. Search by title");
        System.out.println("5. Search by author");
        System.out.println("6. Search by genre");
        System.out.println("7. Show available books");
        System.out.println("8. Show borrowed books");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");
        return input.nextInt();
    }

    private void addBook() {
        System.out.print("Enter Book ID: ");
        Long bookId = input.nextLong();
        System.out.print("Enter Book title: ");
        String title = input.nextLine();
        System.out.print("Enter Book ISBN: ");
        String isbn = input.nextLine();
        List<Author> authors = readAuthors();
        System.out.print("Enter Book genre: ");
        Genre genre = Genre.valueOf(input.nextLine().trim().toUpperCase());
        System.out.print("Enter Book publication date: ");
        LocalDate publicationDate = LocalDate.parse(input.nextLine());

        bookService.addBook(new Book(
                bookId, title, isbn, authors, genre, publicationDate
        ));
        System.out.println("Book added successfully.");
    }

    private List<Author> readAuthors() {
        System.out.print("Enter number of authors: ");
        int authorCount = input.nextInt();

        if (authorCount <= 0) {
            System.out.println("Authors cannot be null or empty.");
            return List.of();
        }

        List<Author> authors = new ArrayList<>();

        for (int i = 1; i <= authorCount; i++) {
            System.out.println("Author " + i + ":");
            System.out.println("1. Use existing author");
            System.out.println("2. Add new author");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Author ID: ");
                    authors.add(authorService.findAuthorById(input.nextLong()));
                    break;
                case 2:
                    authors.add(addAuthor());
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Invalid author choice: " + choice
                    );
            }
        }

        return authors;
    }

    private Author addAuthor() {
        System.out.print("Enter Author ID: ");
        Long authorId = input.nextLong();
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter last name: ");
        String lastName = input.nextLine();
        System.out.print("Enter birth date (YYYY-MM-DD): ");
        LocalDate birthDate = LocalDate.parse(input.nextLine());

        Author author = new Author(authorId, firstName, lastName, birthDate);
        authorService.addAuthor(author);
        System.out.println("Author added successfully.");
        return author;
    }

    private void findBookById() {
        System.out.print("Enter Book ID: ");
        System.out.println(bookService.findBookById(input.nextLong()));
    }

    private void findBookByIsbn() {
        System.out.print("Enter ISBN: ");
        System.out.println(bookService.findByISBN(input.nextLine()));
    }

    private void searchByTitle() {
        System.out.print("Enter title: ");
        input.printResults(bookService.findByTitle(input.nextLine()));
    }

    private void searchByAuthor() {
        System.out.print("Enter author's name: ");
        input.printResults(bookService.findByAuthor(input.nextLine()));
    }

    private void searchByGenre() {
        System.out.print("Enter genre: ");
        input.printResults(bookService.findByGenre(input.nextLine()));
    }

    private void showAvailable() {
        System.out.println("Available Books:");
        input.printResults(statisticsService.getAvailableBooks());
    }

    private void showBorrowed() {
        System.out.println("Borrowed Books:");
        input.printResults(statisticsService.getBorrowedBooks());
    }
}
