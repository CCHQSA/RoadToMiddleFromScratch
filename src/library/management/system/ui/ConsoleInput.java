package library.management.system.ui;

import java.util.List;
import java.util.Scanner;

public class ConsoleInput {

    private final Scanner scanner = new Scanner(System.in);

    public int nextInt() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public long nextLong() {
        long value = scanner.nextLong();
        scanner.nextLine();
        return value;
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void printResults(List<?> results) {
        if (results.isEmpty()) {
            System.out.println("No results found.");
            return;
        }
        results.forEach(System.out::println);
    }
}
