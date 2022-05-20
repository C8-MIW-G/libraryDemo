package nl.miwgroningen.se8.vincent.libraryDemo.repository;

import nl.miwgroningen.se8.vincent.libraryDemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 *
 * Save our books
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
