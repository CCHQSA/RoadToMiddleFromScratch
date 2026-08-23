package Lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    static void main() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"John", "Doe", 2000));
        employees.add(new Employee(2,"Jojo", "Brando", 1000));
        employees.add(new Employee(3,"Risotto", "Nero", 4000));

        employees.forEach(System.out::println);
        List<Employee> salaryMoreThan2000 =
                employees.stream().filter(employee -> employee.getSalary() >= 2000).toList();
        System.out.println("Salary more than 2000 employees");
        salaryMoreThan2000.forEach(System.out::println);

    }
}
