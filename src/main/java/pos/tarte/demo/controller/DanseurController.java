package pos.tarte.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pos.tarte.demo.model.Danseur;
import pos.tarte.demo.repository.DanseurRepository;
import pos.tarte.demo.service.DanseurService;


@Controller
public class DanseurController {
    @Autowired
    private DanseurService danseurService;

    @GetMapping("/danseurs/ajouter")
    public String formAjout(Model model) {
        model.addAttribute("danseur", new Danseur());
        model.addAttribute("modeModification", false);
        return "formulaire-danseur";
    }

    @PostMapping("/danseurs/ajouter")
    public String traitForm(@Valid @ModelAttribute Danseur danseur, BindingResult result) {
        if (result.hasErrors()) {
            return "formulaire-danseur";
        }

        danseurService.ajouter(danseur);
        return "redirect:/danseurs";
    }

    @GetMapping("/danseurs")
    public String listeDanseurs(Model model) {
        model.addAttribute("danseurs", danseurService.obtenirTous());
        return "liste-danseurs";
    }

    @GetMapping("/danseurs/modifier/{id}")
    public String formulaireModification(@PathVariable Long id, Model model) {
        model.addAttribute("danseur", danseurService.obtenirParId(id));
        model.addAttribute("modeModification", true);
        return "formulaire-danseur";
    }
    @PostMapping("/danseurs/modifier/{id}")
    public String traiterModification(@PathVariable Long id,
                                      @Valid @ModelAttribute Danseur danseur,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return "formulaire-danseur";
        }
        danseurService.modifier(id, danseur);
        return "redirect:/danseurs";
    }

    @GetMapping("/danseurs/supprimer/{id}")
    public String supprimerdanseur(@PathVariable Long id) {
        danseurService.supprimer(id);
        return "redirect:/danseurs";
    }
}
