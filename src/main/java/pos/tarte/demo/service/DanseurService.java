package pos.tarte.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.tarte.demo.model.Danseur;
import pos.tarte.demo.repository.DanseurRepository;

import java.util.List;

@Service
public class DanseurService {

    @Autowired
    private DanseurRepository etudiantRepository;

    public List<Danseur> obtenirTous() {
        return etudiantRepository.findAll();
    }

    public Danseur obtenirParId(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Danseur introuvable : " + id));
    }

    public Danseur ajouter(Danseur etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public Danseur modifier(Long id, Danseur etudiant) {
        etudiant.setId(id);
        return etudiantRepository.save(etudiant);
    }

    public void supprimer(Long id) {
        etudiantRepository.deleteById(id);
    }
}
