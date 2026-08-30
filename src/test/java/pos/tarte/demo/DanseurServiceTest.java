package pos.tarte.demo;

import pos.tarte.demo.model.Danseur;
import pos.tarte.demo.repository.DanseurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pos.tarte.demo.service.DanseurService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DanseurServiceTest {

    @Mock
    private DanseurRepository danseurRepository; // faux Repository

    @InjectMocks
    private DanseurService danseurService; // Service testé, avec le mock injecté dedans

    @Test
    void doitRetournerTousLesDanseurs() {
        // Given (préparation)
        Danseur e1 = new Danseur();
        e1.setNom("Rakoto");
        Danseur e2 = new Danseur();
        e2.setNom("Rasoa");
        when(danseurRepository.findAll()).thenReturn(List.of(e1, e2));

        // When (action)
        List<Danseur> resultat = danseurService.obtenirTous();

        // Then (vérification)
        assertThat(resultat).hasSize(2);
        assertThat(resultat.get(0).getNom()).isEqualTo("Rakoto");
    }

    @Test
    void doitLeverUneExceptionSiDanseurIntrouvable() {
        when(danseurRepository.findById(99L)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> danseurService.obtenirParId(99L)
        );
    }

    @Test
    void doitSauvegarderUnDanseur() {
        Danseur danseur = new Danseur();
        danseur.setNom("Rabe");
        when(danseurRepository.save(danseur)).thenReturn(danseur);

        Danseur resultat = danseurService.ajouter(danseur);

        assertThat(resultat.getNom()).isEqualTo("Rabe");
        verify(danseurRepository, times(1)).save(danseur); // vérifie que save() a bien été appelé une fois
    }
}