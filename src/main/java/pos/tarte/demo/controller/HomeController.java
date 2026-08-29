package pos.tarte.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

//import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("titre", "Bienvenue sur mon site");
        model.addAttribute("message", "Ceci est ma première page Spring Boot");
        return "home";
    }

    @GetMapping("/apropos")
    public String greet() {
        return "apropos";
    }

    @GetMapping("/produits")
    public String produits(Model model) {
        model.addAttribute("titre", "Bienvenue sur mon site");
        model.addAttribute("message", "Ceci est ma première page Spring Boot");

        List<String> produits = List.of("Cahier", "Stylo", "Clé USB");
        model.addAttribute("produits", produits);
        return "produits";
    }
}