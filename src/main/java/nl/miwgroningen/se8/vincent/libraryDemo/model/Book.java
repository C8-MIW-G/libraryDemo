package nl.miwgroningen.se8.vincent.libraryDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 *
 * a book available for reading in our library
 */

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long bookId;

    private String title;

    private String author;

    @OneToMany(mappedBy = "book")
    private List<Copy> copies;

    private LocalDate yearOfPublication;

    public int getNumberOfAvailableCopies() {
        int count = 0;
        for (Copy copy : copies) {
            if (copy.getAvailable()) {
                count++;
            }
        }
        return count;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(LocalDate yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }
}
