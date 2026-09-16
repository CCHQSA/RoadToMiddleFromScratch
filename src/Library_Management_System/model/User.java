package Library_Management_System.model;

import java.time.LocalDate;

public class User {
    private final long id;
    private String firstNam;
    private String lastname;
    private String email;
    private LocalDate registrationDate;

    public User(long id, LocalDate registrationDate){
        this.id = id;
        this.registrationDate = LocalDate.now();
    }


}
