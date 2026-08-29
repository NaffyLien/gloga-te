package pos.tarte.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pos.tarte.demo.model.Danseur;
import pos.tarte.demo.repository.DanseurRepository;


@Controller
public class DanseurController {
    @Autowired
    private DanseurRepository danseurRepository;

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

        danseurRepository.save(danseur);
        return "redirect:/danseurs";
    }

    @GetMapping("/danseurs")
    public String listeDanseurs(Model model) {
        model.addAttribute("danseurs", danseurRepository.findAll());
        return "liste-danseurs";
    }
}
