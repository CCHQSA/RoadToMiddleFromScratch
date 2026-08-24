package Date_TimeAPI.Employee_System;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    private final long id;
    private String name;
    LocalDate hireDate;

    public Employee(long id, String name, LocalDate hireDate) {
        this.id = id;
        this.name = name;
        this.hireDate = hireDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public long yearsWorked() {
        return ChronoUnit.YEARS.between(this.hireDate, LocalDate.now());
    }

    public long daysWorked() {
        return ChronoUnit.DAYS.between(this.hireDate, LocalDate.now());
    }

    public List<Employee> hiredThisYear(List<Employee> employees) {
        if (employees.isEmpty()) {
            return new ArrayList<>();
        }
        List<Employee> hiredThisYear = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();

        for (Employee employee : employees) {
            if (employee.getHireDate().getYear() == currentYear) {
                hiredThisYear.add(employee);
            }
        }
        return hiredThisYear;
    }

    public List<Employee> hiredThisMonth(List<Employee> employees) {
        if (employees.isEmpty()) {
            return new ArrayList<>();
        }
        List<Employee> hiredThisMonth = new ArrayList<>();
        LocalDate now = LocalDate.now();

        for (Employee employee : employees) {
            if (employee.getHireDate().getMonth() == now.getMonth() &&
                    employee.getHireDate().getYear() == now.getYear()) {
                hiredThisMonth.add(employee);
            }
        }
        return hiredThisMonth;
    }
}
