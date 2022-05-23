package nl.miwgroningen.se8.vincent.libraryDemo.repository;

import nl.miwgroningen.se8.vincent.libraryDemo.model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy, Long> {
}
