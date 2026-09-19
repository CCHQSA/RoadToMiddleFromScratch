package library.management.system.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Author {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;

    public Author(
            Long id,
            String firstName,
            String lastName,
            LocalDate birthDate
    ) {
        this.id = Objects.requireNonNull(id, "Author ID cannot be null");
        this.firstName = Objects.requireNonNull(firstName, "First name cannot be null");
        this.lastName = Objects.requireNonNull(lastName, "Last name cannot be null");

        this.birthDate = Objects.requireNonNull(
                birthDate,
                "Birth date cannot be null"
        );

        if (this.birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Birth date cannot be in the future"
            );
        }
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge(){
        Period age = Period.between(birthDate, LocalDate.now());
        return age.getYears();
    }
}
