package nl.miwgroningen.se8.vincent.libraryDemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 *
 * Writes books
 */

@Entity @Getter @Setter
public class Author {

    @Id
    @GeneratedValue
    private Long authorId;

    private String firstName;
    private String infixName;
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    Set<Book> booksWritten = new HashSet<>();

    public String getDisplayName() {
        return firstName + " " + infixName + " " + lastName;
    }
}
