package Streams.Employees;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static void main() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", Department.IT, 3000));
        employees.add(new Employee("Kars", Department.HR, 2500));
        employees.add(new Employee("Jorno", Department.IT, 4000));
        employees.add(new Employee("Jotaro", Department.SALES, 3500));
        employees.add(new Employee("Kyto", Department.IT, 4500));

        List<Employee> itEmployees = employees.stream()
                .filter(
                        employee -> employee.getDepartment()
                                .equals(Department.IT))
                .toList();

        Employee highestPaid = employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow();

        Double avgSalary = employees.stream()
                .mapToDouble(Employee::getSalary).sum()
                /
                employees.size();

        Map<Department, List<Employee>> groupByDepartment = employees.stream()
                .collect(Collectors.groupingBy
                        (Employee::getDepartment
                        ));

        Map<Department, Double> avgPerDepartment = employees.stream().
                collect(Collectors.groupingBy(
                        Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary
                        )));

        Map<Department, Double> highestPerDepartment = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getDepartment,
                        Employee::getSalary,
                        Double::max
                ));



    }
}
