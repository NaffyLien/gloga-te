package pos.tarte.demo;

import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import pos.tarte.demo.model.Danseur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pos.tarte.demo.repository.DanseurRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DanseurRepositoryTest {
    @Autowired
    private DanseurRepository danseurRepository;

    @Test
    void doitSauvegarderEtRetrouverUnDanseur() {
        Danseur danseur = new Danseur();
        danseur.setNom("Rakoto");
        danseur.setEmail("rakoto@exemple.com");
        danseur.setAge(20);

        Danseur sauvegarde = danseurRepository.save(danseur);

        assertThat(sauvegarde.getId()).isNotNull();
        assertThat(danseurRepository.findById(sauvegarde.getId())).isPresent();
    }

}
