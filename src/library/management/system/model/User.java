package library.management.system.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class User {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final LocalDate registrationDate;

    public User(
            String firstName,
            String lastName,
            String email,
            LocalDate registrationDate
    ) {
        this(ID_GENERATOR.getAndIncrement(), firstName, lastName, email, registrationDate);
    }

    public User(
            Long id,
            String firstName,
            String lastName,
            String email,
            LocalDate registrationDate
    ) {
        this.id = Objects.requireNonNull(id, "User ID cannot be null");
        this.firstName = Objects.requireNonNull(firstName, "First name cannot be null");
        this.lastName = Objects.requireNonNull(lastName, "Last name cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");

        this.registrationDate = Objects.requireNonNull(
                registrationDate,
                "Registration date cannot be null"
        );

        if (this.registrationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Registration date cannot be in the future"
            );
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public long getMembershipDays(){
        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(registrationDate,today);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + getFullName() + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }

}
