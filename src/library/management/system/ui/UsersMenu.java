package library.management.system.ui;

import library.management.system.model.User;
import library.management.system.service.UserService;

import java.time.LocalDate;

public class UsersMenu {

    private final ConsoleInput input;
    private final UserService userService;

    public UsersMenu(ConsoleInput input, UserService userService) {
        this.input = input;
        this.userService = userService;
    }

    public void run() {
        int choice;
        do {
            choice = showMenu();
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    findUserById();
                    break;
                case 3:
                    findUserByName();
                    break;
                case 4:
                    input.printResults(userService.findAllUsers());
                    break;
                case 5:
                    removeUser();
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
        System.out.println("===== USERS =====");
        System.out.println("1. Add user");
        System.out.println("2. Find user by ID");
        System.out.println("3. Find user by name");
        System.out.println("4. Show all users");
        System.out.println("5. Remove user");
        System.out.println("0. Back");
        System.out.print("Enter your choice: ");
        return input.nextInt();
    }

    private void addUser() {
        System.out.print("Enter User ID: ");
        long id = input.nextLong();
        if (id <= 0) {
            throw new IllegalArgumentException("User ID must be greater than zero");
        }

        System.out.print("Enter first name: ");
        String firstName = readRequiredText("First name");

        System.out.print("Enter last name: ");
        String lastName = readRequiredText("Last name");

        System.out.print("Enter email: ");
        String email = readEmail();

        System.out.print("Enter registration date (YYYY-MM-DD): ");
        LocalDate registrationDate = readRegistrationDate();

        userService.addUser(new User(id, firstName, lastName, email, registrationDate));
        System.out.println("User added successfully.");
    }

    private String readRequiredText(String fieldName) {
        String value = input.nextLine().trim();
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
        return value;
    }

    private String readEmail() {
        String email = readRequiredText("Email");
        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        return email;
    }

    private LocalDate readRegistrationDate() {
        String value = readRequiredText("Registration date");
        try {
            return LocalDate.parse(value);
        } catch (java.time.format.DateTimeParseException exception) {
            throw new IllegalArgumentException(
                    "Registration date must use YYYY-MM-DD format",
                    exception
            );
        }
    }

    private void findUserById() {
        System.out.print("Enter User ID: ");
        System.out.println(userService.findUserById(input.nextLong()));
    }

    private void findUserByName() {
        System.out.print("Enter user name: ");
        input.printResults(userService.findUserByName(input.nextLine()));
    }

    private void removeUser() {
        System.out.print("Enter User ID: ");
        userService.removeUser(input.nextLong());
        System.out.println("User removed successfully.");
    }
}
