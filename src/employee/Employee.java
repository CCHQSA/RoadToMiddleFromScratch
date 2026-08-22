package employee;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Employee {
    private Long id;
    private String name;
    private BigDecimal salary;

    public Employee(Long id, String name, BigDecimal salary) {
        this.id = id;
        this.name = name;
        if (salary.compareTo(BigDecimal.ZERO) <= 0) {
            this.salary = salary;
        }else {
            throw  new IllegalArgumentException("Salary must be greater than zero");
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void raiseSalary(double percentage) {
        if (percentage <= 0) {
            throw new IllegalArgumentException("Percentage must be greater than zero");
        }
        this.salary = this.salary.multiply(BigDecimal.valueOf(percentage / 100.0))
                .setScale(2, RoundingMode.HALF_UP);

    }
}
