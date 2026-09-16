package Library_Management_System.model;

import java.time.LocalDate;
import java.util.List;

public class Book {
    private final long id;
    private String title;
    private String isbn;
    private List<Author> authors;
    private Genre genre;
    private LocalDate publicationDate;
    private boolean isAvailable;

    public Book(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Genre getGenre() {
        return genre;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrow(){
        System.out.println("You borrowed this Book: " + this.title);
        this.isAvailable = false;
    }

    public void returnBook(){
        System.out.println("You returned this Book: " + this.title);
        this.isAvailable = true;
    }


}
