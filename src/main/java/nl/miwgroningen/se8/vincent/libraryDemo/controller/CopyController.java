package nl.miwgroningen.se8.vincent.libraryDemo.controller;

import nl.miwgroningen.se8.vincent.libraryDemo.model.Book;
import nl.miwgroningen.se8.vincent.libraryDemo.model.Copy;
import nl.miwgroningen.se8.vincent.libraryDemo.repository.BookRepository;
import nl.miwgroningen.se8.vincent.libraryDemo.repository.CopyRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Deal with everything regarding copies
 */
@Controller
@RequestMapping("/copy")
public class CopyController {

    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;

    public CopyController(BookRepository bookRepository, CopyRepository copyRepository) {
        this.bookRepository = bookRepository;
        this.copyRepository = copyRepository;
    }

    @GetMapping("/new/{bookId}")
    @Secured("ROLE_USER")
    protected String createNewCopy(@PathVariable("bookId") long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            Copy copy = new Copy();
            copy.setBook(book.get());
            copyRepository.save(copy);
        }
        return "redirect:/books";
    }
}
