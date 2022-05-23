package nl.miwgroningen.se8.vincent.libraryDemo.repository;

import nl.miwgroningen.se8.vincent.libraryDemo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
