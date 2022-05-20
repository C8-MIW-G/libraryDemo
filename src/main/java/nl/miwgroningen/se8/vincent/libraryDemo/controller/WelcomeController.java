package nl.miwgroningen.se8.vincent.libraryDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Welcome the user
 */

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    protected String showWelcomeScreen() {
        return "welcome";
    }

}
