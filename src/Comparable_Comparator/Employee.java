package Comparable_Comparator;

import java.util.Comparator;

public class Employee implements Comparable<Employee> {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public int compareTo(Employee employee) {
        return this.name.compareTo(employee.getName());
    }

    public static class AscendingBySalary implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Double.compare(o1.getSalary(), o2.getSalary());
        }
    }

    public static class DescendingBySalary implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Double.compare(o2.getSalary(), o1.getSalary());
        }
    }

    public static class AscendingByAge implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Integer.compare(o1.getAge(), o2.getAge());
        }
    }

    public static class DescendingByAge implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Integer.compare(o2.getAge(), o1.getAge());
        }
    }

    public static class AscendingByName implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static class DescendingByName implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o2.getName().compareTo(o1.getName());
        }
    }
}
