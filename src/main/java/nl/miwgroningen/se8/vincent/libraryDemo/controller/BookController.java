package nl.miwgroningen.se8.vincent.libraryDemo.controller;

import nl.miwgroningen.se8.vincent.libraryDemo.model.Book;
import nl.miwgroningen.se8.vincent.libraryDemo.repository.AuthorRepository;
import nl.miwgroningen.se8.vincent.libraryDemo.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Facilitate interactions around books
 */

@Controller
public class BookController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping({"/book/all", "/books", "/"})
    protected String showBookOverview(Model model) {
        model.addAttribute("allBooks", bookRepository.findAll());
        return "bookOverview";
    }

    @GetMapping("/book/title/{title}")
    protected String showBookDetailsForTitle(@PathVariable("title") String title, Model model) {
        Optional<Book> book = bookRepository.findByTitle(title);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "bookDetails";
        } else {
            return "redirect:/book/all";
        }
    }

    @GetMapping("/book/id/{bookId}")
    protected String showBookDetails(@PathVariable("bookId") long bookId, Model model) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "bookDetails";
        } else {
            return "redirect:/book/all";
        }
    }

    @GetMapping("/book/update/{bookTitle}")
    protected String showUpdateBookForm(@PathVariable("bookTitle") String bookTitle, Model model) {
        Optional<Book> book = bookRepository.findByTitle(bookTitle);
        if (book.isEmpty()) {
            return "redirect:/books";
        }
        model.addAttribute("book", book.get());
        model.addAttribute("allAuthors", authorRepository.findAll());
        return "bookForm";
    }

    @GetMapping("/book/new")
    protected String createNewBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("allAuthors", authorRepository.findAll());
        return "bookForm";
    }

    @PostMapping("/book/new")
    protected String saveOrUpdateBook(@ModelAttribute("book") Book book, BindingResult result) {
        if (!result.hasErrors()) {
            bookRepository.save(book);
        }
        return "redirect:/book/all";
    }
}
