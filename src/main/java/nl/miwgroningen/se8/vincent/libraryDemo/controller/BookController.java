package nl.miwgroningen.se8.vincent.libraryDemo.controller;

import nl.miwgroningen.se8.vincent.libraryDemo.model.Book;
import nl.miwgroningen.se8.vincent.libraryDemo.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 *
 * Facilitate interactions around books
 */

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book/new")
    protected String createNewBook() {
        Book book = new Book();
        bookRepository.save(book);
        return "welcome";
    }
}
