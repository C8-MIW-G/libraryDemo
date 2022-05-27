package nl.miwgroningen.se8.vincent.libraryDemo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ManyToMany
    private Set<Author> authors = new HashSet<>();

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

    public String getAuthorNames() {
        return String.join(", ", authors.stream().map(Author::getDisplayName).collect(Collectors.toSet()));
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

    public LocalDate getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(LocalDate yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }
}
