package nl.miwgroningen.se8.vincent.libraryDemo;

import nl.miwgroningen.se8.vincent.libraryDemo.model.LibraryUser;
import nl.miwgroningen.se8.vincent.libraryDemo.repository.LibraryUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */

@SpringBootApplication
public class LibraryDemoKickstarter implements CommandLineRunner {

    final LibraryUserRepository libraryUserRepository;
    final PasswordEncoder passwordEncoder;

    public LibraryDemoKickstarter(LibraryUserRepository libraryUserRepository, PasswordEncoder passwordEncoder) {
        this.libraryUserRepository = libraryUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (libraryUserRepository.findByUsername("admin").isEmpty()) {
            LibraryUser admin = new LibraryUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            libraryUserRepository.save(admin);
        }
    }
}
