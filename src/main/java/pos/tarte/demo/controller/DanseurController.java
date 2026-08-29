package pos.tarte.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("modeModification", false);
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

    @GetMapping("/danseurs/modifier/{id}")
    public String formulaireModification(@PathVariable Long id, Model model) {
        Danseur danseur = danseurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("danseur introuvable : " + id));
        model.addAttribute("danseur", danseur);
        model.addAttribute("modeModification", true);
        return "formulaire-danseur"; // on réutilise le même formulaire que pour l'ajout
    }
    @PostMapping("/danseurs/modifier/{id}")
    public String traiterModification(@PathVariable Long id,
                                      @Valid @ModelAttribute Danseur danseur,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return "formulaire-danseur";
        }
        danseur.setId(id); // s'assure qu'on met à jour et non qu'on crée un nouvel enregistrement
        danseurRepository.save(danseur);
        return "redirect:/danseurs";
    }

    @GetMapping("/danseurs/supprimer/{id}")
    public String supprimerdanseur(@PathVariable Long id) {
        danseurRepository.deleteById(id);
        return "redirect:/danseurs";
    }
}
