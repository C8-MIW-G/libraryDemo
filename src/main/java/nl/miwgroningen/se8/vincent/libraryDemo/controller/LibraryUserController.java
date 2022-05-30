package nl.miwgroningen.se8.vincent.libraryDemo.controller;

import nl.miwgroningen.se8.vincent.libraryDemo.model.LibraryUser;
import nl.miwgroningen.se8.vincent.libraryDemo.repository.LibraryUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 *
 * Create and store libary users
 */

@Controller
@RequestMapping("/users")
public class LibraryUserController {

    LibraryUserRepository libraryUserRepository;
    PasswordEncoder passwordEncoder;

    public LibraryUserController(LibraryUserRepository libraryUserRepository, PasswordEncoder passwordEncoder) {
        this.libraryUserRepository = libraryUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/new")
    protected String showUserForm(Model model) {
        model.addAttribute("user", new LibraryUser());
        return "userForm";
    }

    @PostMapping("/new")
    protected String saveOrUpdateUser(@ModelAttribute("user") LibraryUser user, BindingResult result) {
        if (result.hasErrors()) {
            return "userForm";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        libraryUserRepository.save(user);
        return "redirect:/";
    }
}
