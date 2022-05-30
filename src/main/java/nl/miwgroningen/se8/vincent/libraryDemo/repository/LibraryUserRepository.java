package nl.miwgroningen.se8.vincent.libraryDemo.repository;

import nl.miwgroningen.se8.vincent.libraryDemo.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 */
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {
    Optional<LibraryUser> findByUsername(String username);
}
