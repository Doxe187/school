package ch.bbw.cardgame;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Aufgabe 4 – Spring Boot Web-App
 * Zeigt movies, studios und people aus der moviesdb an.
 */
@Controller
public class MoviesController {

    private final MoviesNoSQLConnector connector = new MoviesNoSQLConnector();

    @GetMapping("/movies")
    public String showMovies(Model model) {
        model.addAttribute("movies", connector.getAllMovies());
        return "movies";
    }

    @GetMapping("/studios")
    public String showStudios(Model model) {
        model.addAttribute("studios", connector.getAllStudios());
        return "studios";
    }

    @GetMapping("/people")
    public String showPeople(Model model) {
        model.addAttribute("people", connector.getAllPeople());
        return "people";
    }
}
