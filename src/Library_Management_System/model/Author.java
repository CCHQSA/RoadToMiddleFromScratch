package Library_Management_System.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.spi.LocaleServiceProvider;

public class Author {
    private final long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public  Author(long id, String firstName, String lastName, LocalDate birthDate) {
        this.id = id;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public int getAge(){
        Period age = Period.between(birthDate, LocalDate.now());
        return age.getYears();
    }
}
