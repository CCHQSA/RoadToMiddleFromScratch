package Lambdas;

import java.util.Comparator;

public class Sort {

    public static class SortBySalaryAsc implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            if (o1.getSalary() > o2.getSalary()) {
                return 1;
            }
            if (o1.getSalary() < o2.getSalary()) {
                return -1;
            }
            return 0;
        }
    }

    public static class SortBySalaryDesc implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            if (o1.getSalary() < o2.getSalary()) {
                return -1;
            }
            if (o1.getSalary() > o2.getSalary()) {
                return 1;
            }
            return 0;
        }
    }

    public static class SortByEmployeeFirstNameAsc implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
    }
    public static class SortByEmployeeFirstNameDesc implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
    }

    public static class SortByEmployeeLastNameAsc implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getLastName().compareTo(o2.getLastName());
        }
    }

    public static class SortByEmployeeLastNameDesc implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getLastName().compareTo(o2.getLastName());
        }
    }


}
