package pos.tarte.demo.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pos.tarte.demo.model.Danseur;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DanseurController {
    private List<Danseur> listDanseurs = new ArrayList<>();

    @GetMapping("/danseurs/ajouter")
    public String formAjout(Model model) {
        model.addAttribute("danseur", new Danseur());
        return "formulaire-danseur";
    }

    @PostMapping("/danseurs/ajouter")
    public String traitForm(@Valid @ModelAttribute Danseur danseur, BindingResult result) {
        if (result.hasErrors()) {
            return "formulaire-danseur";
        }

        listDanseurs.add(danseur);
        return "redirect:/danseurs";
    }

    @GetMapping("/danseurs")
    public String listeDanseurs(Model model) {
        model.addAttribute("danseurs", listDanseurs);
        return "liste-danseurs";
    }
}
