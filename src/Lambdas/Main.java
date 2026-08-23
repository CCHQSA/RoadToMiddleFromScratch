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


        List<Employee> sortBySalaryAsc = employees.stream().sorted(new Sort.SortBySalaryAsc()).toList();
        List<Employee> sortBySalaryDesc = employees.stream().sorted(new Sort.SortBySalaryDesc()).toList();
        List<Employee> sortByFirstNameAsc = employees.stream().sorted(new Sort.SortByEmployeeFirstNameAsc()).toList();
        List<Employee> sortByFirstNameDesc = employees.stream().sorted(new Sort.SortByEmployeeFirstNameDesc()).toList();
        List<Employee> sortByLastNameAsc = employees.stream().sorted(new Sort.SortByEmployeeLastNameAsc()).toList();
        List<Employee> sortByLastNameDesc = employees.stream().sorted(new Sort.SortByEmployeeLastNameAsc()).toList();
        System.out.println("Sort by salary asc");
        sortBySalaryAsc.forEach(System.out::println);
        System.out.println("Sort by salary desc");
        sortByFirstNameDesc.forEach(System.out::println);
        System.out.println("Sort by first name asc");
        sortByFirstNameAsc.forEach(System.out::println);
        System.out.println("Sort by first name desc");
        sortByFirstNameDesc.forEach(System.out::println);
        System.out.println("Sort by last name asc");
        sortByLastNameAsc.forEach(System.out::println);
        System.out.println("Sort by last name desc");
        sortByLastNameDesc.forEach(System.out::println);



    }
}
