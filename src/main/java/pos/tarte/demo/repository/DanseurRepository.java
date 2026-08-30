package pos.tarte.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.tarte.demo.model.Danseur;

public interface DanseurRepository extends JpaRepository<Danseur, Long> {
}
