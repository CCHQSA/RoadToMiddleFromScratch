package Streams;

import java.util.List;

public class Main {
    static void main() {
        List<Integer> numbers =
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.stream()
                .filter(number -> number % 2 == 0)
                .forEach(num -> System.out.print(num + " "));
        System.out.println();

        numbers.stream()
                .map(num -> num * num)
                .forEach(num -> System.out.print(num + " "));
        System.out.println();

        System.out.println(numbers.stream().mapToInt(Integer::intValue).sum());
        System.out.println();

        System.out.println(numbers.stream().mapToInt(Integer::intValue).sum() / numbers.size());
        System.out.println();

        numbers.stream().max(Integer::compareTo).ifPresent(System.out::println);
        System.out.println();

        numbers.stream().min(Integer::compareTo).ifPresent(System.out::println);



    }
}
